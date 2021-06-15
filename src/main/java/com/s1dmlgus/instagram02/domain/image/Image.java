package com.s1dmlgus.instagram02.domain.image;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.s1dmlgus.instagram02.domain.BaseTimeEntity;
import com.s1dmlgus.instagram02.domain.comment.Comment;
import com.s1dmlgus.instagram02.domain.likes.Likes;
import com.s1dmlgus.instagram02.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = {"likes"})
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


    // 유저
    @JsonIgnoreProperties({"images"})   // user에서 images를 리턴할 때, 하지 말아라
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;                  // 누가 업로드 했는지



    // 좋아요
    @JsonIgnoreProperties({"image"})    // likes에서 image를 호출하지 말아라
    @OneToMany(mappedBy = "image",fetch = FetchType.LAZY)
    private List<Likes> likes;

    
    // DB에 저장되지 않는 필드
    // 좋아요 상태확인
    @Transient
    private boolean likeState;
    // 좋아요 개수
    @Transient
    private int likeCount;



    // 댓글
    @OrderBy("id DESC")
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image",fetch = FetchType.LAZY)
    private List<Comment> comments;





    // 좋아요 설정
    public void setLikeState(boolean likeState) {
        this.likeState = likeState;
    }

    // 좋아요 개수 설정
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
