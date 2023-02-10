package com.alexgim.sharing.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostCommentReq {
    @NotBlank
    private String content;
}
