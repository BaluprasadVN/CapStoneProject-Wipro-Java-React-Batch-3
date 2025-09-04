package com.example.bus.dto;

public class AuthDtos {
    public static class LoginRequest {
        public String email;
        public String password;
    }
    public static class RegisterRequest {
        public String name;
        public String email;
        public String phone;
        public String password;
    }
    public static class AuthResponse {
        public String accessToken;
        public Long userId;
        public String name;
        public String email;
        public String role;
    }
}
