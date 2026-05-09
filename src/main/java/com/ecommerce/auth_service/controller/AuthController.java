package com.ecommerce.auth_service.controller;

import com.ecommerce.auth_service.entity.Role;
import com.ecommerce.auth_service.entity.User;
import com.ecommerce.auth_service.repository.UserRepository;
import com.ecommerce.auth_service.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER USER
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        if (user.getEmail().contains("admin")) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    // LOGIN USER
    @PostMapping("/login")
    public String loginUser(@RequestBody User loginData) {

        User user =
                userRepository.findByEmail(
                        loginData.getEmail()
                );

        if (user == null) {
            return "User Not Found";
        }

        if (
                !passwordEncoder.matches(
                        loginData.getPassword(),
                        user.getPassword()
                )
        ) {
            return "Invalid Password";
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}