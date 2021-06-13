package com.s1dmlgus.instagram02.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {


    // 네이티브 쿼리

    // 구독하기
    @Modifying
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createdDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);


    // 구독취소
    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);


    // 구독여부  (  fromUserId : 현재 로그인 아이디, toUserId : 페이지아이디)
    // 구독 했으면 : 1, 안했으면 : 0
    // SELECT COUNT(*) FROM subscribe WHERE fromUserId = 3 AND toUserId = 2;
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserID = :pageUserId", nativeQuery = true)
    int mSubscribeState(@Param("principalId") Long principalId, @Param("pageUserId") Long pageUserId);



    // 구독자 수
    // SELECT COUNT(*) FROM subscribe WHERE fromUserId = 3;
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
    int mSubscribeCount(@Param("pageUserId") Long pageUserId);




}
