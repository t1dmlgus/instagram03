package com.s1dmlgus.instagram02.web.dto.image;


import com.s1dmlgus.instagram02.domain.image.Image;
import com.s1dmlgus.instagram02.domain.likes.Likes;
import com.s1dmlgus.instagram02.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDto {


    private List<Image> image;



}
