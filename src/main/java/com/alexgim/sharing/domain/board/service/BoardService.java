package com.alexgim.sharing.domain.board.service;

import com.alexgim.sharing.board.data.dto.*;
import com.alexgim.sharing.common.BaseException;
import com.alexgim.sharing.domain.board.data.dto.*;

public interface BoardService {
    PostBoardResp save(PostBoardReq postBoardReq) throws BaseException;

    GetBoardAllResp readAll();

    GetBoardResp read(Long boardId);

    PutBoardResp update(PutBoardReq putBoardReq);

    void delete(Long boardId);
}
