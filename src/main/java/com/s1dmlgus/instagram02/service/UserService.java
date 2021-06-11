package com.s1dmlgus.instagram02.service;

import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.domain.subscribe.SubscribeRepository;
import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.domain.user.UserRepository;
import com.s1dmlgus.instagram02.exception.CustomException;
import com.s1dmlgus.instagram02.exception.CustomValidationException;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import com.s1dmlgus.instagram02.web.dto.auth.JoinDto;
import com.s1dmlgus.instagram02.web.dto.user.UserProfileDto;
import com.s1dmlgus.instagram02.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // 회원 프로필
    @Transactional
    public UserProfileDto profile(UserProfileDto userProfileDto, Long sessionUserId) {

        // 해당 유저에 대한 User + image를 다 가져온다.

        // 유저 유무확인
        User userEntity = existUser(userProfileDto.getPageUserId(), "[프로필] 해당 프로필 페이지는 없슈");

        // 구독 상태
        boolean subscribeState = isSubscribeState(userProfileDto, sessionUserId);

        System.out.println("subscribeState = " + subscribeState);
        // 구독자 수
        int subscribeCount = getSubscribeCount(userProfileDto);
        System.out.println("subscribeCount = " + subscribeCount);


        // 프로필 dto 세팅
        userProfileDto.setProfile(userProfileDto, sessionUserId, userEntity, subscribeState, subscribeCount);
        



        return userProfileDto;

    }

    // 회원가입

    @Transactional
    public User join(JoinDto joinDto) {

        User user = joinDto.toEntity();

        // 1. 중복확인
        if (!duplicateUsername(user)) {

            // 2. 패스워드 암호화
            bcryptPw(user);
            // 3. 권한부여
            setRole(user);
            // 4. 영속화
            User saveUser = userRepository.save(user);

            return saveUser;
        }

        throw new CustomValidationException("[회원가입] 현재 사용중인 Id 입니다.");
    }

    /**
     * 회원수정
     *
     * 1. 영속화
     * 2. 영속화된 오브젝트 수정   -> 더티체킫 (업데이트 완료)
     *
     * @param id
     * @param userUpdateDto
     * @return
     */
    @Transactional
    public ResponseDto<?> updateUser(Long id, UserUpdateDto userUpdateDto) {

        // 영속화
        User user = existUser(id, "해당 아이디가 없습니다.");

        // 업데이트 로직, DTO -> Entity
        user.updateUser(userUpdateDto.toEntity());

        return new ResponseDto<>("회원수정완02료!", user);
    }


    // 업데이트 화면

    @Transactional
    public void showUpdateUser(Long id, User sessionUser) {

        User user1 = existUser(id, "[회원수정] 해당 아이디가 없습니다.");
        accorUser(sessionUser, user1);

    }



    // 구독자 수 조회
    private int getSubscribeCount(UserProfileDto userProfileDto) {
        int subscribeCount = subscribeRepository.mSubscribeCount(userProfileDto.getPageUserId());
        return subscribeCount;
    }

    // 구독 확인
    private boolean isSubscribeState(UserProfileDto userProfileDto, Long sessionUserId) {
        int state = subscribeRepository.mSubscribeState(sessionUserId, userProfileDto.getPageUserId());
        boolean subscribeState = state == 1;
        return subscribeState;
    }

    // 유저 일치확인

    private void accorUser(User sessionUser, User user1) {
        boolean equals = user1.getId().equals(sessionUser.getId());

        if (!equals) {
            throw new CustomException("[회원수정] 해당 페이지는 접근할 수 없습니다.");
        }
    }

    // 유저 유무확인

    private User existUser(Long id, String s) {

        return userRepository.findById(id).orElseThrow(() -> {
            return new CustomException(s);

        });
    }


    // 유저 중복확인
    private Boolean duplicateUsername(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }


    // 비밀번호 암호화
    private void bcryptPw(User user) {

        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.bcryptPw(encode);
    }

    // 권한부여
    private void setRole(User user) {
        String role = "ROLE_USER";
        user.setRole(role);
    }


}
