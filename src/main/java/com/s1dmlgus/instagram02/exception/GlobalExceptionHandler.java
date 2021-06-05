package com.s1dmlgus.instagram02.exception;


import com.s1dmlgus.instagram02.util.Script;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice
public class GlobalExceptionHandler {


    // 유효성 에러
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ResponseDto<?>> validationException(CustomValidationException e) {

        return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    // 유효성 에러 - image
    @ExceptionHandler(CustomImageValidationException.class)
    public String imageValidationException(CustomImageValidationException e) {

        return Script.back(e.getMessage());

    }


    @ExceptionHandler(CustomException.class)
    public String customException(CustomException e) {

        return Script.back(e.getMessage());

    }

}
