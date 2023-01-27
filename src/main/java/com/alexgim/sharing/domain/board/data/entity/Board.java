package com.alexgim.sharing.domain.board.data.entity;

import com.alexgim.sharing.domain.board.data.BoardStatus;
import com.alexgim.sharing.common.BaseEntity;
import com.alexgim.sharing.domain.image.data.entity.Image;
import com.alexgim.sharing.domain.user.data.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Data
@Builder
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String tag;

    @Column(nullable = false)
    private String area;

    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private BoardStatus status;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Image> image = new ArrayList<>();

}
