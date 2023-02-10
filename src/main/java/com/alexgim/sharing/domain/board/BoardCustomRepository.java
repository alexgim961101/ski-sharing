package com.alexgim.sharing.domain.board;

import com.alexgim.sharing.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardCustomRepository {

    Page<Board> findAllBoard10Size(Pageable pageable);
}
