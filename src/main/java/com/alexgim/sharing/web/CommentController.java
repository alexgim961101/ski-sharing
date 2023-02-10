package com.alexgim.sharing.web;

import com.alexgim.sharing.handler.ex.BaseException;
import com.alexgim.sharing.handler.ex.BaseResponseStatus;
import com.alexgim.sharing.service.comment.CommentService;
import com.alexgim.sharing.util.JwtService;
import com.alexgim.sharing.web.dto.BaseResponseDto;
import com.alexgim.sharing.web.dto.comment.PostCommentReq;
import com.alexgim.sharing.web.dto.comment.PostCommentResp;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtService jwtService;
    /**
     * 댓글 쓰기
     * */
    @PostMapping("/{boardId}")
    public BaseResponseDto postComment(@PathVariable Long boardId, @Valid @RequestBody PostCommentReq postCommentReq, BindingResult bindingResult) {
        checkValidation(bindingResult);

        if(boardId < 1) throw new BaseException(BaseResponseStatus.INVALID_REQUEST);

        Long userId;
        try {
            userId = jwtService.getUserId();
        } catch (Exception e){
            throw new BaseException(BaseResponseStatus.EMPTY_JWT);
        }
        PostCommentResp postCommentResp = commentService.writeComment(userId, boardId, postCommentReq);
        return new BaseResponseDto(postCommentResp);
    }



    /**
     * 게시물 댓글 불러오기 (페이징 5개씩)
     * */

    /**
     * 자식 댓글 쓰기
     * */
    @PostMapping("/{boardId}/{commentId}")
    public BaseResponseDto childCommentWrite(@PathVariable Long boardId, @PathVariable Long commentId, @Valid @RequestBody PostCommentReq postCommentReq, BindingResult bindingResult) {
        checkValidation(bindingResult);
        if(boardId < 1 || commentId < 1) throw new BaseException(BaseResponseStatus.INVALID_REQUEST);

        Long userId = jwtService.getUserId();
        PostCommentResp postCommentResp = commentService.writeChildComment(userId, boardId, commentId, postCommentReq);
        return new BaseResponseDto(postCommentResp);
    }

    /**
     * 자식 댓글 불러오기
     * */

    /**
     * 댓글 수정
     * */

    /**
     * 댓글 삭제
     * */

    public void checkValidation(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new BaseException(BaseResponseStatus.INVALID_REQUEST, errorMap);
        }
    }
}
