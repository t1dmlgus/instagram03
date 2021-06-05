package com.s1dmlgus.instagram02.service;



import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {


    @Autowired
    private UserRepository userRepository;


    @Test
    public void 회원가입() throws Exception{
        //given

        User user = User.builder()
                .username("t1")
                .password("1234")
                .email("sdd@naver.com")
                .name("s2ssd")
                .build();

        //when
        Boolean b = existSS(user);
        System.out.println("b = " + b);


        if(b){

        }
        //then
    }


    private Boolean existSS(User user) {
        Boolean b = userRepository.existsByUsername(user.getUsername());
        return b;
    }

}