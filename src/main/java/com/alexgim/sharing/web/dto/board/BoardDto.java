package com.alexgim.sharing.web.dto.board;

import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.web.dto.image.ImageDto;
import com.alexgim.sharing.web.dto.user.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoardDto {
    private long id;
    private UserDto user;
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private Long price;
    private int count;
    private List<ImageDto> imageList;
}
