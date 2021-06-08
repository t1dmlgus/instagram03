package com.s1dmlgus.instagram02.web.dto.user;


import com.s1dmlgus.instagram02.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserUpdateDto {

    @NotBlank
    @Size(max = 20, message = "이름은 20자 내 가능합니02다.")
    private String name;                // 필수


    private String website;
    private String bio;
    private String email;
    private String phone;
    private String gender;



    public User toEntity(){

        return User.builder()
                .name(name)
                .website(website)
                .bio(bio)
                .email(email)
                .phone(phone)
                .gender(gender)
                .build();

    }

}
