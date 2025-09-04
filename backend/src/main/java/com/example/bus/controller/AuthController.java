package com.example.bus.controller;

import com.example.bus.dto.AuthDtos.*;
import com.example.bus.model.Role;
import com.example.bus.model.User;
import com.example.bus.repository.UserRepository;
import com.example.bus.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest req){
        if (userRepository.existsByEmail(req.email)) {
            throw new RuntimeException("Email already used");
        }
        if (userRepository.existsByPhone(req.phone)) {
            throw new RuntimeException("Phone already used");
        }
        User user = new User();
        user.setName(req.name);
        user.setEmail(req.email);
        user.setPhone(req.phone);
        user.setPassword(passwordEncoder.encode(req.password));
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);

        AuthResponse res = new AuthResponse();
        res.userId = user.getId();
        res.name = user.getName();
        res.email = user.getEmail();
        res.role = user.getRole().name();
        res.accessToken = jwtUtil.generateToken(user.getEmail(), Map.of("role", user.getRole().name(), "uid", user.getId()));
        return res;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req){
        User user = userRepository.findByEmail(req.email).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(req.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        AuthResponse res = new AuthResponse();
        res.userId = user.getId();
        res.name = user.getName();
        res.email = user.getEmail();
        res.role = user.getRole().name();
        res.accessToken = jwtUtil.generateToken(user.getEmail(), Map.of("role", user.getRole().name(), "uid", user.getId()));
        return res;
    }
}
