package com.s1dmlgus.instagram02.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {


//    @Modifying
//    @Query(value = "INSERT INTO comment(content, imageId, userId, createdDate) VALUES(:content, :imageId, :userId, now()", nativeQuery = true)
//    Comment mSave(@Param("content") String content,@Param("imageId") Long imageId, @Param("userId") Long userId);
}
