package com.s1dmlgus.instagram02.web.controller.api;

import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.exception.CustomValidationException;
import com.s1dmlgus.instagram02.service.UserService;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import com.s1dmlgus.instagram02.web.dto.auth.JoinDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;



@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthApiController {


    private final UserService userService;


    // 회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseDto<?>> join(@Valid JoinDto joinDto, BindingResult bindingResult) {


            log.info(joinDto.toString());

            // 회원가입
            User saveUser = userService.join(joinDto);


        return new ResponseEntity<>(new ResponseDto<>("회원가입되었습니다:)",""), HttpStatus.OK);

    }

}
