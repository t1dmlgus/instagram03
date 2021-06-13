package com.s1dmlgus.instagram02.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRespository extends JpaRepository<Likes, Long> {


    @Modifying
    @Query(value = "INSERT INTO likes(imageId, userId, createdDate) VALUES(:imageId, :principalId, now())", nativeQuery = true)
    void mLikes(@Param("imageId") Long imageId, @Param("principalId") Long principalId);


    @Modifying
    @Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :principalId", nativeQuery = true)
    void mDisLikes(@Param("imageId")Long imageId, @Param("principalId") Long principalId);


}
