package com.s1dmlgus.instagram02.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRespository extends JpaRepository<Image, Long> {


    @Query(value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId) ORDER BY id DESC", nativeQuery = true)
    Page<Image> mStory(@Param("principalId") Long principalId, Pageable pageable);


}
