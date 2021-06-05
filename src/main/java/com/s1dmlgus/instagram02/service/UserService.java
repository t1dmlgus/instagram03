package com.s1dmlgus.instagram02.service;

import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.domain.user.UserRepository;
import com.s1dmlgus.instagram02.exception.CustomException;
import com.s1dmlgus.instagram02.exception.CustomValidationException;
import com.s1dmlgus.instagram02.web.dto.auth.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * 1. 해당 유저가 가지고 있는 사진을 다 가져온다.
     *     -> select * from image where userId = :userId;
     *     -> jpa 이용
     *     ->
     *
     *
     *
     *
     * @param userId
     * @return
     */
    // 회원 프로필
    @Transactional
    public User profile(Long userId) {
        //select * from image where userId = :userId;

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            return new CustomException("해당 프로필 페이지는 없슈");
        });

//        System.out.println(" ======================= ");
//        userEntity.getImages().get(0);


        return userEntity;


    }



    // 회원가입
    @Transactional
    public User Join(JoinDto joinDto) {

        User user = joinDto.toEntity();

        // 1. 중복확인
        if (!existUsername(user)) {

            // 2. 패스워드 암호화
            bcryptPw(user);
            // 3. 권한부여
            setRole(user);
            // 4. 영속화
            User saveUser = userRepository.save(user);

            return saveUser;
        }

        throw new CustomValidationException("현재 사용중인 Id 입니다.");
    }








    // 중복확인
    private Boolean existUsername(User user) {
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
