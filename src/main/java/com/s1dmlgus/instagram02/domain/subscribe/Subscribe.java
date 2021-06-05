package com.s1dmlgus.instagram02.domain.subscribe;


import com.s1dmlgus.instagram02.domain.BaseTimeEntity;
import com.s1dmlgus.instagram02.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk",
                        columnNames = {"fromUserId", "toUserId"}
                )
        }
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Subscribe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true)                    // 필드 -> 유니크
    @JoinColumn(name = "fromUserId")            // db 컬럼명
    @ManyToOne
    private User fromUser;                     // 구독하는 유저

    @JoinColumn(name = "toUserId")
    @ManyToOne
    private User toUser;                       // 구독받는 유저



}