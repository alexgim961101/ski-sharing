package com.alexgim.sharing.domain.user.data.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResDto {
    private Long userId;

    private String nickname;  // 유저의 아이디

    private String img;

    private String email;
}
