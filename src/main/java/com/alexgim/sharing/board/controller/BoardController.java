package com.alexgim.sharing.board.controller;

import com.alexgim.sharing.board.data.dto.PostBoardReq;
import com.alexgim.sharing.board.data.dto.PostBoardResp;
import com.alexgim.sharing.board.service.BoardService;
import com.alexgim.sharing.config.BaseException;
import com.alexgim.sharing.config.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

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
