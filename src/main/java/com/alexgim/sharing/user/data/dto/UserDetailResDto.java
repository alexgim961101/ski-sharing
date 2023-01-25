package com.alexgim.sharing.user.data.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailResDto {
    private Long userId;
    private String nickname;
    private String img;
    private String addr;
    private String content;
    private String email;
}
