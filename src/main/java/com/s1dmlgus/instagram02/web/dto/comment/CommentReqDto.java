package com.s1dmlgus.instagram02.web.dto.comment;



import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class CommentReqDto {

    @Size(max = 30)
    @NotBlank(message = "댓글을 입력해 주세요")                       // 빈값(""),빈공백문자열(" "), null 체크
    private String content;
    @NotNull                       // 빈값("") 체크
    private Long imageId;

}
