package com.alexgim.sharing.domain.board;

import com.alexgim.sharing.domain.BaseEntity;
import com.alexgim.sharing.domain.image.Image;
import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.web.dto.BoardStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private BoardStatusType status;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Image> imageList = new ArrayList<>();
}
