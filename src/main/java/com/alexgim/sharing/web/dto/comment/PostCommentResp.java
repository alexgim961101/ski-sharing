package com.alexgim.sharing.web.dto.comment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostCommentResp {
    private Long userId;
    private String nickname;
    private String img;
    private Long boardId;
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
}
