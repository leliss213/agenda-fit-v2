package com.example.agenda_fit_v2.service;

import com.example.agenda_fit_v2.repository.UserRepository;

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
}
