package com.alexgim.sharing.board.service.impl;

import com.alexgim.sharing.board.data.BoardStatus;
import com.alexgim.sharing.board.data.dto.*;
import com.alexgim.sharing.board.data.entity.Board;
import com.alexgim.sharing.board.data.repository.BoardRepository;
import com.alexgim.sharing.board.service.BoardService;
import com.alexgim.sharing.config.BaseException;
import com.alexgim.sharing.config.BaseResponseStatus;
import com.alexgim.sharing.user.data.entity.User;
import com.alexgim.sharing.user.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostBoardResp save(PostBoardReq postBoardReq) throws BaseException {
        User user = userRepository.findById(postBoardReq.getUserId()).orElseThrow(() -> new BaseException(BaseResponseStatus.DB_SAVE_FAILED));
        Board board = boardRepository.save(Board.builder()
                .userId(user)
                .title(postBoardReq.getTitle())
                .content(postBoardReq.getContent())
                .tag(postBoardReq.getTag())
                .area(postBoardReq.getArea())
                .startDate(LocalDate.parse(postBoardReq.getStartDate(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .endDate(LocalDate.parse(postBoardReq.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .price(postBoardReq.getPrice())
                .status(BoardStatus.ING).build());

        return PostBoardResp.builder()
                .boardId(board.getBoardId())
                .userId(board.getUserId())
                .title(board.getTitle())
                .content(board.getContent())
                .tag(board.getTag())
                .startDate(board.getStartDate())
                .endDate(board.getEndDate())
                .price(board.getPrice())
                .status(board.getStatus()).build();
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
