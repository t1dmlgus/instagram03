package com.s1dmlgus.instagram02.service;


import com.s1dmlgus.instagram02.config.auth.PrincipalDetails;
import com.s1dmlgus.instagram02.domain.image.Image;
import com.s1dmlgus.instagram02.domain.image.ImageRespository;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import com.s1dmlgus.instagram02.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Log4j2
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRespository imageRespository;


    // 이미지 스토리 가져오기
    @Transactional(readOnly = true)
    public ResponseDto imageStory(Long principalId, Pageable pageable){

        Page<Image> images = imageRespository.mStory(principalId, pageable);


        // 좋아요 가져오기
        images.forEach(image->{
            // 좋아요 개수
            log.info("좋아요 개수");
            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach(likes -> {

                // 해당 이미지에 좋아요 한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한 것인지 비교
                if (likes.getUser().getId().equals(principalId)) {
                    image.setLikeState(true);
                }
            });

        });


        return new ResponseDto("이미지 스토리 가져오기",images);
    }








    @Value("${file.path}")
    private String uploadFolder;


    // 이미지 업로드
    @Transactional
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
            e.printStackTrace();        // 에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력한다.


        }

        
        // image 테이블에 저장
        Image image = imageUploadDto.toEntity(filename, principalDetails.getUser());
        Image imageEntity = imageRespository.save(image);

        System.out.println("imageEntity = " + imageEntity);


    }

}
