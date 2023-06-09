package com.MADG2.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUser {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;
    
}
