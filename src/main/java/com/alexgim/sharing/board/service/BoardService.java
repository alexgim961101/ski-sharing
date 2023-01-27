package com.alexgim.sharing.board.service;

import com.alexgim.sharing.board.data.dto.*;
import com.alexgim.sharing.config.BaseException;

public interface BoardService {
    PostBoardResp save(PostBoardReq postBoardReq) throws BaseException;

    GetBoardAllResp readAll();

    GetBoardResp read(Long boardId);

    PutBoardResp update(PutBoardReq putBoardReq);

    void delete(Long boardId);
}
