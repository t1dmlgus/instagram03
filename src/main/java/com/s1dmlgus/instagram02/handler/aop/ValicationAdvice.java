package com.s1dmlgus.instagram02.handler.aop;


import com.s1dmlgus.instagram02.exception.CustomException;
import com.s1dmlgus.instagram02.exception.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

// RestController, Service 등 모든 것들이 Component를 상속해서 만들어 졌다.
@Component          // 메모리에 띄워야하는 어노테이션
@Aspect             // AOP 처리를 할 수 있는 핸들러
public class ValicationAdvice {

    //@Before       -> 어떤 특정함수가 실행 전에 실행
    //@After       -> 어떤 특정함수가 실행 후에 실행
    //@Around       -> 어떤 특정함수 전, 후로 실행
    
    @Around("execution(* com.s1dmlgus.instagram02.web.controller.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println(" web api 컨트롤러============================");

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {

                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError fieldError : bindingResult.getFieldErrors()) {
                        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실021패", errorMap);

                }


            }
        }

        return proceedingJoinPoint.proceed();

    }
    @Around("execution(* com.s1dmlgus.instagram02.web.controller.*Controller.*(..))")
    public Object Advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println(" web 컨트롤러============================");

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                // 매개변수 -> bindingResult로 다운캐스팅
                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError fieldError : bindingResult.getFieldErrors()) {
                        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    }
                    throw new CustomException("유효성 검사 실02패!");

                }


            }
        }



        return proceedingJoinPoint.proceed();

    }


}
