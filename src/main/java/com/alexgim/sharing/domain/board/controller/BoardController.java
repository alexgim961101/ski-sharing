package com.alexgim.sharing.domain.board.controller;

import com.alexgim.sharing.domain.board.data.dto.PostBoardReq;
import com.alexgim.sharing.domain.board.data.dto.PostBoardResp;
import com.alexgim.sharing.domain.board.service.BoardService;
import com.alexgim.sharing.common.BaseException;
import com.alexgim.sharing.common.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@Validated
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping()
    public BaseResponse enrollContent(@ModelAttribute PostBoardReq postBoardReq) throws BaseException {
        PostBoardResp save = boardService.save(postBoardReq);
        return new BaseResponse(save);
    }
}
