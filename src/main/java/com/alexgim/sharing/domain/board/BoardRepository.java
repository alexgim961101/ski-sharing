package com.alexgim.sharing.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {
    // Page<Board> findAll(Pageable pageable);
}
