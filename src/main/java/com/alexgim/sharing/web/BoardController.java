package com.alexgim.sharing.web;

import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.handler.ex.BaseException;
import com.alexgim.sharing.handler.ex.BaseResponseStatus;
import com.alexgim.sharing.service.board.BoardService;
import com.alexgim.sharing.web.dto.BaseResponseDto;
import com.alexgim.sharing.web.dto.board.BoardDto;
import com.alexgim.sharing.web.dto.board.GetBoardResp;
import com.alexgim.sharing.web.dto.board.PostBoardReq;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;

    @ApiOperation(value = "게시판 등록", notes = "게시판 등록을 위한 API")
    @PostMapping("/{userId}")
    public BaseResponseDto createBoard(@PathVariable Long userId, @Valid @ModelAttribute PostBoardReq postBoardReq, BindingResult bindingResult) {
        checkValidation(bindingResult);
        log.info("요청 DTO 값 확인 : {}", postBoardReq.toString());
        Board boardEntity = boardService.save(userId, postBoardReq);
        return new BaseResponseDto(boardEntity);
    }

    @ApiOperation(value = "게시판 출력 API", notes = "10개씩 게시판 출력")
    @GetMapping
    public BaseResponseDto readTen(Pageable pageable) {
        List<GetBoardResp> list = boardService.readAll(pageable);
        return new BaseResponseDto(list);
    }

    @ApiOperation(value = "특정 게시판 출력 API", notes = "특정 게시판의 내용 확인")
    @GetMapping("/{boardId}")
    public BaseResponseDto readOne(@PathVariable Long boardId){
        if(boardId < 1) throw new BaseException(BaseResponseStatus.INVALID_REQUEST);

        BoardDto boardDto = boardService.readOne(boardId);
        return new BaseResponseDto(boardDto);
    }

    public void checkValidation(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new BaseException(BaseResponseStatus.INVALID_REQUEST, errorMap);
        }
    }
}
