package com.s1dmlgus.instagram02.web.controller;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.service.UserService;
import com.s1dmlgus.instagram02.web.dto.user.UserProfileDto;
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
    @GetMapping("/user/{pageUserId}")
    public String profile(UserProfileDto userProfileDto, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {


        // 프로필 뷰 렌더링 서비스
        UserProfileDto profile = userService.profile(userProfileDto, principalDetails.getUser().getId());

        model.addAttribute("dto", profile);
        model.addAttribute("principal", principalDetails);

        return "user/profile";

    }

    // 회원수정
    @GetMapping("/user/{id}/update")
    public String UpdateUser(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        // 업데이트 페이지로직
        userService.showUpdateUser(id, principalDetails.getUser());


        model.addAttribute("principal", principalDetails);
        return "user/update";
    }



}
