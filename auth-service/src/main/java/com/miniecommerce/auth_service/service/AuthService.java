package com.miniecommerce.auth_service.service;

import com.miniecommerce.auth_service.entity.User;
import com.miniecommerce.auth_service.repository.UserRepository;
import com.miniecommerce.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String login(String username, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔴 Modified: pass userId also
        return jwtUtil.generateToken(
                user.getId(),                     // userId
                user.getUsername(),
                user.getRole().name()
        );
    }
}