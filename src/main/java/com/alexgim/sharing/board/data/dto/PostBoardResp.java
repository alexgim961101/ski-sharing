package com.alexgim.sharing.board.data.dto;

import com.alexgim.sharing.board.data.BoardStatus;
import com.alexgim.sharing.user.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostBoardResp {

    private Long boardId;

    private User userId;

    private String title;

    private String content;

    private String tag;

    private String area;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long price;

    private BoardStatus status;
}
