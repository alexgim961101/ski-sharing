package com.alexgim.sharing.image.data.entity;

import com.alexgim.sharing.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne
    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @ManyToOne
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imageUrl;
}
