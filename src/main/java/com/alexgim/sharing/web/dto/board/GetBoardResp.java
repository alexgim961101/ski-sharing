package com.alexgim.sharing.web.dto.board;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetBoardResp {
    // 유저 아이디
    private String nickname;
    // 게시판 제목
    private String title;
    // 게시판 업데이트 날짜
    private LocalDateTime updatedAt;
    // 조회수
    private int count;
}
