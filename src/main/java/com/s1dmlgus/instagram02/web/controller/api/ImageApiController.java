package com.s1dmlgus.instagram02.web.controller.api;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.domain.image.Image;
import com.s1dmlgus.instagram02.service.ImageService;
import com.s1dmlgus.instagram02.service.LikesService;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;


    // 이미지 스토리
    @GetMapping("/image")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size = 3) Pageable pageable) {

        ResponseDto responseDto = imageService.imageStory(principalDetails.getUser().getId(),pageable);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 좋아요
    @PostMapping("/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PathVariable Long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        ResponseDto<?> likesDto = likesService.iLikes(imageId, principalDetails.getUser().getId());

                                // HTTP 상태코드(201) - DB에 데이터를 넣었다.
        return new ResponseEntity<>(likesDto, HttpStatus.CREATED);


    }


    // 좋아요 취소
    @DeleteMapping("/image/{imageId}/likes")
    public ResponseEntity<?> disLikes(@PathVariable Long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        ResponseDto<?> disLikesDto = likesService.disLikes(imageId, principalDetails.getUser().getId());


        return new ResponseEntity<>(disLikesDto, HttpStatus.OK);
    }




}
