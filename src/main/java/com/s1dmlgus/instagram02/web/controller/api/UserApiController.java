package com.s1dmlgus.instagram02.web.controller.api;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.exception.CustomValidationException;
import com.s1dmlgus.instagram02.service.UserService;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import com.s1dmlgus.instagram02.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {


    private final UserService userService;

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
        ResponseDto responseDto = userService.updateUser(id, userUpdateDto, principalDetails);

        return new ResponseEntity<>(new ResponseDto<>("회원수정 완료!", responseDto), HttpStatus.OK);
    }

}