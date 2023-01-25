package com.alexgim.sharing.user.data.dto;

import com.alexgim.sharing.user.data.RoleType;
import com.alexgim.sharing.user.data.entiry.User;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @ApiParam(value = "닉네임", required = true)
    @Size(min = 5, max = 20)
    @NotEmpty
    private String nickname;

    @ApiParam(value = "비밀번호", required = true)
    @Pattern(regexp = "^[a-zA-Z\\\\d`~!@#$%^&*()-_=+]{8,24}$")  // 영어 + 숫자 + 숫자와 관련된 특수문자만 허용
    @NotEmpty
    @Size(min = 7)
    private String password;

    @ApiParam(value = "프로필 사진", required = false)
    private String img;

    @ApiParam(value = "주소", required = true)
    @NotEmpty
    private String addr;

    @ApiParam(value = "자기소개", required = false)
    private String content;

    @ApiParam(value = "이메일", required = true)
    @Email
    private String email;

    public User toEntity() {
        return User.builder()
                .nickname(this.nickname)
                .password(this.password)
                .img(this.img)
                .addr(this.addr)
                .content(this.content)
                .email(this.email)
                .status(RoleType.NORMAL).build();
    }
}
