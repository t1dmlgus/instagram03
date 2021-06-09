package com.s1dmlgus.instagram02.web.controller.api;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.service.SubscribeService;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscrbeApiController {

    private final SubscribeService subscribeService;

    // 구독하기
    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {

        subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);

        return new ResponseEntity<>(new ResponseDto<>("구독하기 성공", null), HttpStatus.OK);
    }




    // 구독 취소하기
    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId){

        subscribeService.unSubscribe(principalDetails.getUser().getId(), toUserId);

        return new ResponseEntity<>(new ResponseDto<>("구독 취소하기 성공", null), HttpStatus.OK);
    }


}
