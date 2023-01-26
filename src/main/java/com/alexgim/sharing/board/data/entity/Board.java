package com.alexgim.sharing.board.data.entity;

import com.alexgim.sharing.board.data.BoardStatus;
import com.alexgim.sharing.common.BaseEntity;
import com.alexgim.sharing.image.data.entity.Image;
import com.alexgim.sharing.user.data.entity.User;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "board")
@Builder
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @Column(name = "user_id", nullable = false)
    private User userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String tag;

    @Column(nullable = false)
    private String area;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private BoardStatus status;

    @OneToMany
    private List<Image> image;

}
