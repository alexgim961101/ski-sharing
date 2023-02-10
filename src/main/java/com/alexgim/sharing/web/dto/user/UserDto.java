package com.alexgim.sharing.web.dto.user;

import com.alexgim.sharing.web.dto.UserStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String nickname;
    private String img;
    private String phone;
    private String email;
    private UserStatusType status;
}
