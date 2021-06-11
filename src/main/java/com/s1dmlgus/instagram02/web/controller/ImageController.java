package com.s1dmlgus.instagram02.web.controller;

import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.exception.CustomImageValidationException;
import com.s1dmlgus.instagram02.exception.CustomValidationException;
import com.s1dmlgus.instagram02.service.ImageService;
import com.s1dmlgus.instagram02.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;


    // 이미지 업로드 페이지 호출
    @GetMapping("/image/upload")
    public String ImageUpload(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){


        model.addAttribute("principal", principalDetails);

        return "image/upload";
    }

    // 이미지 업로드
    @PostMapping("/image")
    public String ImageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        System.out.println("imageUploadDto = " + imageUploadDto);
        System.out.println("principalDetails = " + principalDetails);

        if (imageUploadDto.getFile().isEmpty()) {

            throw new CustomImageValidationException("이미지가 첨부되지 않았습니다.");
        }


        // 서비스 호출
        imageService.imageUpload(imageUploadDto, principalDetails);

        return "redirect:/user/" + principalDetails.getUser().getId();

    }



}
