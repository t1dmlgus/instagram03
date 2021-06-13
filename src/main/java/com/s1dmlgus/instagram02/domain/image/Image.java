package com.s1dmlgus.instagram02.domain.image;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.s1dmlgus.instagram02.domain.BaseTimeEntity;
import com.s1dmlgus.instagram02.domain.user.User;
import lombok.*;

import javax.persistence.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Image extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 추후 필요기능
    // + 이미지 좋아요
    // + 댓글
    // + 추가기능

    private String caption;
    private String postImageUrl;        // 사진을 전송받아서 그 사진을 서버의 특정 폴더에 저장 -> DB에 저장된 경로를 삽입, INSERT


    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;                  // 누가 업로드 했는지




}
