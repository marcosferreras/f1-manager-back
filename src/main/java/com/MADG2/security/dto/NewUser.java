package com.MADG2.security.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class NewUser {

    @NotBlank
    private String name;

    @NotBlank
    private String userName;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Set<String> roles = new HashSet<>();
}
