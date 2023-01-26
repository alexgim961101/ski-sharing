package com.alexgim.sharing.board.service.impl;

import com.alexgim.sharing.board.data.dto.*;
import com.alexgim.sharing.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private final BoardService boardService;

    @Override
    public PostBoardResp save(PostBoardReq postBoardReq) {
        return null;
    }

    @Override
    public GetBoardAllResp readAll() {
        return null;
    }

    @Override
    public GetBoardResp read(Long boardId) {
        return null;
    }

    @Override
    public PutBoardResp update(PutBoardReq putBoardReq) {
        return null;
    }

    @Override
    public void delete(Long boardId) {

    }
}
