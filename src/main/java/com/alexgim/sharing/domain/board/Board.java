package com.alexgim.sharing.domain.board;

import com.alexgim.sharing.domain.BaseEntity;
import com.alexgim.sharing.domain.comment.Comment;
import com.alexgim.sharing.domain.image.Image;
import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.web.dto.BoardStatusType;
import com.alexgim.sharing.web.dto.board.BoardDto;
import com.alexgim.sharing.web.dto.image.ImageDto;
import com.alexgim.sharing.web.dto.user.UserDto;
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

    @ManyToOne(fetch = FetchType.LAZY)
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
    private int count;

    @Column(nullable = false)
    private BoardStatusType status;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, targetEntity = Comment.class)
    private List<Comment> commentList = new ArrayList<>();

    public BoardDto toDto(){
        List<ImageDto> imageDtoList = new ArrayList<>();

        for(Image image : imageList){
            imageDtoList.add(image.toDto());
        }

        return BoardDto.builder()
                .id(id)
                .user(user.toDto())
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .price(price)
                .count(count)
                .imageList(imageDtoList)
                .build();
    }
}
