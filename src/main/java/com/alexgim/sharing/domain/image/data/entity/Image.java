package com.alexgim.sharing.domain.image.data.entity;

import com.alexgim.sharing.domain.board.data.entity.Board;
import com.alexgim.sharing.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imageUrl;
}