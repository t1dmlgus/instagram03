package com.s1dmlgus.instagram02.domain.likes;


import com.s1dmlgus.instagram02.domain.BaseTimeEntity;
import com.s1dmlgus.instagram02.domain.image.Image;
import com.s1dmlgus.instagram02.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"imageId", "userId"}
                )
        }
)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Likes extends BaseTimeEntity {     // N

    // 어떤 이미지를 누가 좋아했는지

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "imageId")
    @ManyToOne
    private Image image;     // 1


    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;       // 1



}
