package com.alexgim.sharing.service.board;

import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.web.dto.board.BoardDto;
import com.alexgim.sharing.web.dto.board.GetBoardResp;
import com.alexgim.sharing.web.dto.board.PostBoardReq;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    
    /**
     * 게시판 저장 로직
     * */
    Board save(Long userId, PostBoardReq postBoardReq);

    List<GetBoardResp> readAll(Pageable pageable);

    BoardDto readOne(Long boardId);

//    void readAll(Pageable pageable);
}
