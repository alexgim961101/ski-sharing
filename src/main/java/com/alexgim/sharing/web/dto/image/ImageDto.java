package com.alexgim.sharing.web.dto.image;

import com.alexgim.sharing.web.dto.board.BoardDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class ImageDto {
    private Long id;
    @ToString.Exclude
    @JsonIgnore
    private BoardDto board;
    private String name;
    private String url;
}
