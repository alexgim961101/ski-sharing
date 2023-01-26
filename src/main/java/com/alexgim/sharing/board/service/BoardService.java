package com.alexgim.sharing.board.service;

import com.alexgim.sharing.board.data.dto.*;

public interface BoardService {
    PostBoardResp save(PostBoardReq postBoardReq);

    GetBoardAllResp readAll();

    GetBoardResp read(Long boardId);

    PutBoardResp update(PutBoardReq putBoardReq);

    void delete(Long boardId);
}
