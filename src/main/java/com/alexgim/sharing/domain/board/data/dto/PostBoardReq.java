package com.alexgim.sharing.domain.board.data.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostBoardReq {
    private Long userId;

    private String title;

    private String content;

    private String tag;

    private String area;

    private String startDate;

    private String endDate;

    private Long price;

    private List<MultipartFile> imageList;
}
