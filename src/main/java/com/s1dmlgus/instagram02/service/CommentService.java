package com.s1dmlgus.instagram02.service;


import com.s1dmlgus.instagram02.domain.comment.Comment;
import com.s1dmlgus.instagram02.domain.comment.CommentRepository;
import com.s1dmlgus.instagram02.domain.image.Image;
import com.s1dmlgus.instagram02.domain.user.User;
import com.s1dmlgus.instagram02.domain.user.UserRepository;
import com.s1dmlgus.instagram02.exception.CustomException;
import com.s1dmlgus.instagram02.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    // 댓글 쓰기
    @Transactional
    public ResponseDto<Comment> writeComment(String content, Long imageId, Long userId){

        //commentRepository.mSave(content, imageId, userId);

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException("해당 아이디는 없습니다.");
        });


        Image image = Image.builder()
                .id(imageId)
                .build();


        Comment comment = Comment.builder()
                .content(content)
                .user(userEntity)
                .image(image)
                .build();


        Comment saveComment = commentRepository.save(comment);

        return new ResponseDto<>("해당 댓글 저장완02료!", saveComment);
    }

    
    // 댓글 삭제
    @Transactional
    public void deleteComment(Long id){

        try {
            commentRepository.deleteById(id);

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

}
