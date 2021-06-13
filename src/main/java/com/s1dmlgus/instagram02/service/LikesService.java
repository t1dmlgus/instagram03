package com.s1dmlgus.instagram02.service;


import com.s1dmlgus.instagram02.domain.likes.LikesRespository;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRespository likesRespository;


    // 좋아요
    @Transactional
    public ResponseDto<?> iLikes(Long imageId, Long principalId) {

        likesRespository.mLikes(imageId, principalId);

        return new ResponseDto<>("좋아요 성공!", null);
    }

    // 좋아요 취소
    @Transactional
    public ResponseDto<?> disLikes(Long imageId, Long principalId) {

        likesRespository.mDisLikes(imageId, principalId);

        return new ResponseDto<>("좋아요 취소 성공!", null);
    }

}
