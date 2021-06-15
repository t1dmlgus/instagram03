package com.s1dmlgus.instagram02.web.controller.api;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.exception.CustomValidationException;
import com.s1dmlgus.instagram02.service.SubscribeService;
import com.s1dmlgus.instagram02.service.UserService;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import com.s1dmlgus.instagram02.web.dto.subscribe.SubscribeDto;
import com.s1dmlgus.instagram02.web.dto.user.UserProfileDto;
import com.s1dmlgus.instagram02.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {


    private final UserService userService;
    private final SubscribeService subscribeService;


    @PutMapping("/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable Long principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // 프로필 이미지 수정
        UserProfileDto userProfileDto = userService.updateProfileImage(principalId, profileImageFile);
        // 세션 업데이트
        principalDetails.setUser(userProfileDto.getUser());

        return new ResponseEntity<>("프로필 이미지 변경 완02료!", HttpStatus.OK);
    }





    // 프로필유저의 구독 리스트 가져오기
    @GetMapping("/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable Long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {


        ResponseDto<?> responseDto = subscribeService.subscribeList(principalDetails.getUser().getId(), pageUserId);


        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }


    // 회원수정
    @PutMapping("/user/{id}/update")
    public ResponseEntity<?> 회원수정(@PathVariable Long id,
                                  @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {

            Map<String, String> errorMap = new HashMap<>();


            for (FieldError fieldError : bindingResult.getFieldErrors()) {

                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                System.out.println(" ======================== ");
                System.out.println(fieldError.getField() +" : " + fieldError.getDefaultMessage());
                System.out.println(" ======================== ");
            }
            throw new CustomValidationException("유효성 검사 실02패", errorMap);
        }


        // 회원수정 로직
        ResponseDto<?> responseDto = userService.updateUser(id, userUpdateDto);

        // 세션 반영
        principalDetails.setUser((User) responseDto.getData());

        System.out.println("22222222" );

        System.out.println("responseDto = " + responseDto);

        System.out.println("33222222" );

        ResponseEntity<? extends ResponseDto<?>> ss = new ResponseEntity<>(responseDto, HttpStatus.OK);

        System.out.println("ss = " + ss);

        System.out.println("44222222" );

        return ss;
        // MessageConverter -> 응답시에 UserEntity의 모든 getter 함수가 호출되고, JSON으로 파싱하여 응답한다.
    }

}