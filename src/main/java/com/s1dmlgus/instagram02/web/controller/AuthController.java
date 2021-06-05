package com.s1dmlgus.instagram02.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // 로그인 폼
    @GetMapping("/auth/signin")
    public String login(){
        return "auth/signin";
    }

    // 회원가입
    @GetMapping("/auth/signup")
    public String join(){
        return "auth/signup";
    }



}
