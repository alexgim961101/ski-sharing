package com.alexgim.sharing.service.comment;

import com.alexgim.sharing.web.dto.comment.PostCommentReq;
import com.alexgim.sharing.web.dto.comment.PostCommentResp;

public interface CommentService {
    PostCommentResp writeComment(Long userId, Long boardId, PostCommentReq postCommentReq);
}
