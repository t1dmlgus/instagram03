package com.s1dmlgus.instagram02.config.oauth;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OAuth2DetailService extends DefaultOAuth2UserService {


    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());


        Map<String, Object> userInfo = oAuth2User.getAttributes();
        String username = "facebook_" + userInfo.get("id");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        //String name = (String)userInfo.get("name");
        // 패스워드는 무조건 암호화 되어서 들어가야한다! -> Bcypt


        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {           // 페이스북 최초 로그인

            // 유저 정보 만들기 -> SignDTO 참고
            User user01 = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .name(name)
                    .role("ROLE_USER")
                    .build();

            return new PrincipalDetails(userRepository.save(user01));           // 리턴 -> 세션에 저장

        }else {                             // 페이스북으로 이미 회원가입이 되어있음

            return new PrincipalDetails(userEntity);

        }
    }
}
