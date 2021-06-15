package com.s1dmlgus.instagram02.web.dto.user;


import com.s1dmlgus.instagram02.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {

    private Long pageUserId;                        // 현재 페이지 유저 id

    private boolean PageOwnerState;                 // 현재 페이지 주인(true: 페이지 주인, false: 주인아님)
    private User user;                              // User

    private int imageCount;                         // 유저가 가지고 있는 이미지 개수


    private boolean subscribeState;                 // 구독상태
    private int subscribeCount;                     // 구독자 수




    // 프로필 세팅
    public void setProfile(UserProfileDto userProfileDto, Long sessionUserId, User userEntity, boolean subscribeState, int subscribeCount){

        setUser(userEntity);
        setPageOwnerState(userProfileDto.getPageUserId().equals(sessionUserId));
        setImageCount(userEntity.getImages().size());
        setSubscribeState(subscribeState);
        setSubscribeCount(subscribeCount);
    }

    // 프로필 이미지 수정 생성자
    public UserProfileDto(User user) {
        this.user = user;
    }
}
