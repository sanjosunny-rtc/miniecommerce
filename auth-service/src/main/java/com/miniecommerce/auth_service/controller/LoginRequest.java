package com.miniecommerce.auth_service.controller;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

}