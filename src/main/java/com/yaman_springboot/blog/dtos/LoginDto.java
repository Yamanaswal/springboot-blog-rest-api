package com.yaman_springboot.blog.dtos;

import lombok.Data;

@Data
public class LoginDto {

    private String usernameOrEmail;

    private String password;
}
