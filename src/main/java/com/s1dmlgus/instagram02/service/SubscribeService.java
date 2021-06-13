package com.s1dmlgus.instagram02.service;


import com.s1dmlgus.instagram02.domain.subscribe.SubscribeRepository;
import com.s1dmlgus.instagram02.exception.CustomException;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import com.s1dmlgus.instagram02.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@RequiredArgsConstructor
@Service
public class SubscribeService {


    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;


    // 해당 프로필유저의 구독 리스트 서비스
    @Transactional(readOnly = true)
    public ResponseDto<?> subscribeList(Long principalId, Long pageUserId) {


        System.out.println("principalId222 = " + principalId);
        System.out.println("pageUserId222 = " + pageUserId);


        // 쿼리 준비
        String sb = "SELECT u.id, u.username, u.profileImageUrl, " +
                "if((SELECT true FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeState, " +
                "if((?=u.id), 1, 0) equalUserState " +
                "FROM user u INNER JOIN subscribe s " +
                "ON u.id = s.toUserId " +
                "WHERE s.fromUserId = ? ";

        // 1. 물음표 principalId
        // 2. 물음표 principalId
        // 3. 물음표 pageUserId

        // 쿼리 완성
        Query nativeQuery = em.createNativeQuery(sb)
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);


        // 쿼리 실행(qlrm 라이프러리 -> dto에 DB결과를 매핑하기 위해서)
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(nativeQuery, SubscribeDto.class);


        return new ResponseDto<>("구독자 정보 리스트 가져오기 성공!", subscribeDtos);

    }



    // 구독하기
    @Transactional
    public void subscribe(Long fromUserId, Long toUserId){


            try {

                subscribeRepository.mSubscribe(fromUserId, toUserId);

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
