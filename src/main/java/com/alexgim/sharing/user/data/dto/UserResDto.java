package com.alexgim.sharing.user.data.dto;

import com.alexgim.sharing.user.data.RoleType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
