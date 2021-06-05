package com.s1dmlgus.instagram02.domain.user;


import com.s1dmlgus.instagram02.domain.BaseTimeEntity;
import com.s1dmlgus.instagram02.domain.image.Image;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 번호 증가 전략이 데이터베이스를 따라간다.
    private Long id;


    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;

    private String bio;                         // 자기소개개
    private String website;                     // 웹사이트
    private String phone;
    private String gender;
    private String profileImageUrl;             // 사진
    private String role;                        // 권한


    /**
     *  1. 나는 연관관계 주인이 아니다. -> 그러니까, 테이블에 컬럼을 만들지마!
     *  2. User를 select 할 때, 해당 userId로 등록된 image들을 다 가져와!
     *  3. Lazy = User를 select 할 때, 해당 User id로 등록된 image를 가져오지마 -> 대신 getImages() 할수의 image 들이 호출될 때 가져와!(한번 더 select)
     *  4. Eager = User를 select 할 때, 해당 User id로 등록된 image들을 전부 join 해서 가져와!
     */

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images;

    
    

    // 비밀번호 암호화
    public void bcryptPw(String encode) {
        password = encode;
    }
    
    // 권한부여
    public void setRole(String role) {
        this.role = role;
    }
}
