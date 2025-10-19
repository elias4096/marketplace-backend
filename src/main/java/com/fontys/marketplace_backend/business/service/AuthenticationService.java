package com.fontys.marketplace_backend.business.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fontys.marketplace_backend.persistence.entity.User;
import com.fontys.marketplace_backend.persistence.repository.UserRepository;
import com.fontys.marketplace_backend.persistence.requests.LoginRequest;
import com.fontys.marketplace_backend.persistence.requests.SignupRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(SignupRequest input) {
        User user = User.builder()
                .displayName(input.getDisplayName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()));

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
