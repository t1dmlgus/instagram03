package com.s1dmlgus.instagram02.web.dto.subscribe;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {

    private BigInteger id;
    private String username;
    private String profileImageUrl;
    private int subscribeState;             // 로그인 유저가 현재 유저 구독했는지 확인
    private int equalUserState;             // 로그인 유저와 동일 확인




}
