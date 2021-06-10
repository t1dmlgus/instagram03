package com.s1dmlgus.instagram02.service;


import com.s1dmlgus.instagram02.domain.subscribe.SubscribeRepository;
import com.s1dmlgus.instagram02.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {


    private final SubscribeRepository subscribeRepository;


    // 구독하기
    @Transactional
    public void subscribe(Long fromUserId, Long toUserId){


            try {
                if (!fromUserId.equals(toUserId)) {
                    subscribeRepository.mSubscribe(fromUserId, toUserId);
                }

            }catch (Exception e){

                throw new CustomException("이미 구독을 하였습니다.");
            }

    }
    
    
    // 구독취소하기
    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId){

        subscribeRepository.mUnSubscribe(fromUserId, toUserId);


    }

}
