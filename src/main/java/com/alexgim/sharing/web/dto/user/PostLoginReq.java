package com.alexgim.sharing.web.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostLoginReq {
    @NotBlank
    @Size(min = 6, max = 20)
    private String nickname;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
}
