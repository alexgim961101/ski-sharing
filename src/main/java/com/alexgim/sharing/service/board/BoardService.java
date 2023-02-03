package com.alexgim.sharing.service.board;

import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.web.dto.board.PostBoardReq;

public interface BoardService {
    
    /**
     * 게시판 저장 로직
     * */
    Board save(Long userId, PostBoardReq postBoardReq);
}
