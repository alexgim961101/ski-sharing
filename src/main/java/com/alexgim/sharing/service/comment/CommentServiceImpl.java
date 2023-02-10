package com.alexgim.sharing.service.comment;

import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.domain.board.BoardRepository;
import com.alexgim.sharing.domain.comment.Comment;
import com.alexgim.sharing.domain.comment.CommentRepository;
import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.domain.user.UserRepository;
import com.alexgim.sharing.web.dto.CommentStatus;
import com.alexgim.sharing.web.dto.comment.PostCommentReq;
import com.alexgim.sharing.web.dto.comment.PostCommentResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public PostCommentResp writeComment(Long userId, Long boardId, PostCommentReq postCommentReq) {
        Board boardEntity = boardRepository.findById(boardId).get();
        User userEntity = userRepository.findById(userId).get();

        Comment commentEntity = commentRepository.save(Comment.builder()
                .user(userEntity)
                .superComment(null)
                .content(postCommentReq.getContent())
                .status(CommentStatus.EXIST)
                .board(boardEntity)
                .build());

        boardEntity.getCommentList().add(commentEntity);
        userEntity.getCommentList().add(commentEntity);

        return PostCommentResp.builder()
                .userId(userEntity.getId())
                .nickname(userEntity.getNickname())
                .img(userEntity.getImg())
                .boardId(boardId)
                .commentId(commentEntity.getId())
                .content(commentEntity.getContent())
                .createdAt(commentEntity.getCreatedAt())
                .build();
    }
}
