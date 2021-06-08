package com.s1dmlgus.instagram02.web.controller;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 프로필
    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id, Model model) {


        User userEntity = userService.profile(id);
        model.addAttribute("user", userEntity);

        return "user/profile";

    }

    // 회원수정
    @GetMapping("/user/{id}/update")
    public String UpdateUser(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        // 업데이트 페이지로직
        userService.showUpdateUser(id, principalDetails.getUser());


        model.addAttribute("principal", principalDetails.getUser());
        return "user/update";
    }



}
