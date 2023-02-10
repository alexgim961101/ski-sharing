package com.alexgim.sharing.service.board;

import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.web.dto.board.PostBoardReq;

import java.awt.print.Pageable;

public interface BoardService {
    
    /**
     * 게시판 저장 로직
     * */
    Board save(Long userId, PostBoardReq postBoardReq);

//    void readAll(Pageable pageable);
}
