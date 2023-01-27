package com.alexgim.sharing.domain.board.data.repository;

import com.alexgim.sharing.domain.board.data.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
