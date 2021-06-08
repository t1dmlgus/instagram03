package com.s1dmlgus.instagram02.web.controller.api;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.service.UserService;
import com.s1dmlgus.instagram02.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {

    private final UserService userService;


    @PutMapping("/user/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        System.out.println("id = " + id);
        System.out.println("userUpdateDto = " + userUpdateDto);
        System.out.println("principalDetails = " + principalDetails);


        userService.updateUser(id, userUpdateDto, principalDetails);





        return null;

    }


}
