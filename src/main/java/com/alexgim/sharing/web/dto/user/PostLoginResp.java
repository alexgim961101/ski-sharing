package com.alexgim.sharing.web.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLoginResp {
    private Long id;
    private String nickname;
    private String img;
    private String jwt;
}
