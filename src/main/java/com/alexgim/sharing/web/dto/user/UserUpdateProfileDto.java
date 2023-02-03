package com.alexgim.sharing.web.dto.user;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateProfileDto {
    @ApiParam(value = "파일", required = true)
    @NotBlank
    private MultipartFile file;
}
