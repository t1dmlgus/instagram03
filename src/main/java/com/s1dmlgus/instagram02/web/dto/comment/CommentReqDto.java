package com.s1dmlgus.instagram02.web.dto.comment;



import lombok.Data;


@Data
public class CommentReqDto {

    private String content;
    private Long imageId;

}
