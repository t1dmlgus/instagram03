package com.s1dmlgus.instagram02.config.auth;

import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    /**
     * 1. 영속성 컨텍스트 에서 username 확인
     * 
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username111 = " + username);
        User userEntity = userRepository.findByUsername(username);

        if (userEntity != null) {
            System.out.println("userEntity222 = " + userEntity);
            return new PrincipalDetails(userEntity);
        }
        System.out.println(" 더스티;번 ");
        return null;

    }
}
