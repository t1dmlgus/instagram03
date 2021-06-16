package com.s1dmlgus.instagram02.web.controller.api;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.domain.comment.Comment;
import com.s1dmlgus.instagram02.exception.CustomValidationException;
import com.s1dmlgus.instagram02.service.CommentService;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import com.s1dmlgus.instagram02.web.dto.comment.CommentReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    // 댓글 쓰기
    @PostMapping("/comment")
    public ResponseEntity<?> saveComment(@Valid @RequestBody CommentReqDto commentReqDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){



        ResponseDto<Comment> commentResponseDto = commentService.writeComment(commentReqDto.getContent(), commentReqDto.getImageId(), principalDetails.getUser().getId());


        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    
    
    // 댓글 삭제
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){


        commentService.deleteComment(id);

        return new ResponseEntity<>(new ResponseDto<>("댓글 삭제 성공", null), HttpStatus.OK);
    }


}
