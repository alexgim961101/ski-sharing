package com.alexgim.sharing.web.dto.board;

import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.domain.image.Image;
import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.web.dto.BoardStatusType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostBoardReq {
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private Long price;
    private List<MultipartFile> fileList;

    public Board toEntity(User user) {
        return Board.builder()
                .user(user)
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .price(price)
                .count(0)
                .status(BoardStatusType.ING).build();
    }
}
