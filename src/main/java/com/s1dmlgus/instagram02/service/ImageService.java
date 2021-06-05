package com.s1dmlgus.instagram02.service;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.domain.image.Image;
import com.s1dmlgus.instagram02.domain.image.ImageRespository;
import com.s1dmlgus.instagram02.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRespository imageRespository;

    @Value("${file.path}")
    private String uploadFolder;


    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {

        UUID uuid = UUID.randomUUID();// 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약
        String filename = uuid +"_"+ imageUploadDto.getFile().getOriginalFilename();
        System.out.println("filename = " + filename);


        // 저장경로
        Path path = Paths.get(uploadFolder + filename);
        System.out.println("path = " + path);


        // 통신, I/O -> 예외발생 할 수 있다.
        try {

            Files.write(path, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {

        }


        Image image = imageUploadDto.toEntity(filename, principalDetails.getUser());
        Image imageEntity = imageRespository.save(image);

        System.out.println("imageEntity = " + imageEntity);


    }

}
