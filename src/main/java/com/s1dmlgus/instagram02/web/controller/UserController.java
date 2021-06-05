package com.s1dmlgus.instagram02.web.controller;


import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id, Model model) {

        User userEntity = userService.profile(id);

        model.addAttribute("user", userEntity);

        return "user/profile";

    }



}
