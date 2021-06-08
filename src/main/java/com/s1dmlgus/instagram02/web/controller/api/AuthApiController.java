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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;


@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthApiController {


    private final UserService userService;


    // 회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseDto<?>> join(@Valid JoinDto joinDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(" 에러 감시~~~~ ");
            HashMap<String, String> errorMap = new HashMap<>();

            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());

                System.out.println(" ================================= ");
                System.out.println("fieldError.getDefaultMessage() = " + fieldError.getDefaultMessage());
                System.out.println(" ================================= ");
            }
            throw new CustomValidationException("유효성 검사 실02패", errorMap);

        }

            log.info(joinDto.toString());

            // 회원가입
            User saveUser = userService.join(joinDto);


        return new ResponseEntity<>(new ResponseDto<>("회원가입되었습니다:)",""), HttpStatus.OK);

    }

}
