package com.s1dmlgus.instagram02.service;


import com.s1dmlgus.instagram02.domain.subscribe.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {


    private final SubscribeRepository subscribeRepository;


    // 구독하기
    @Transactional
    public void Subscribe(Long fromUserId, Long toUserId){


            System.out.println(" here ");
            subscribeRepository.mSubscribe(fromUserId, toUserId);
            System.out.println("fddgd " );


    }
    
    
    // 구독취소하기
    @Transactional
    public void UnSubscribe(Long fromUserId, Long toUserId){

        subscribeRepository.mUnSubscribe(fromUserId, toUserId);


    }

}
