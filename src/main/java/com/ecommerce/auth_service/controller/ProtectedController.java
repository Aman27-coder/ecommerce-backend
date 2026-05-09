package com.ecommerce.auth_service.controller;

import com.ecommerce.auth_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/protected")
    public String protectedRoute(@RequestHeader("Authorization") String authHeader) {

        // REMOVE "Bearer "
        String token = authHeader.substring(7);

        // VALIDATE TOKEN
        if (!jwtUtil.validateToken(token)) {
            return "Invalid Token";
        }

        String email = jwtUtil.extractEmail(token);

        return "Welcome " + email + " to protected API 🚀";
    }
}