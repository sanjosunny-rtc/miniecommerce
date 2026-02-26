package com.miniecommerce.auth_service.controller;

import com.miniecommerce.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }

}