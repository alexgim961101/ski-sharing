package com.alexgim.sharing.web.dto.user;

import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.web.dto.UserStatusType;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserLoginReqDto {

    @ApiParam(value = "닉네임", required = true)
    @NotBlank
    @Size(min = 6, max = 20)
    private String nickname;

    @ApiParam(value = "비밀번호", required = true)
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @ApiParam(value = "이메일", required = true)
    @Email
    private String email;

    @ApiParam(value = "휴대폰 번호", required = true)
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "올바른 휴대폰 형식이 아닙니다(xxx-xxxx-xxxx)")
    private String phone;

    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .password(password)
                .email(email)
                .status(UserStatusType.OK)
                .phone(phone).build();
    }
}
