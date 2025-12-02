package com.fontys.marketplace_backend.presentation.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.fontys.marketplace_backend.business.service.AuthenticationService;
import com.fontys.marketplace_backend.business.service.JwtService;
import com.fontys.marketplace_backend.persistence.entity.User;
import com.fontys.marketplace_backend.persistence.requests.LoginRequest;
import com.fontys.marketplace_backend.persistence.requests.SignupRequest;
import com.fontys.marketplace_backend.persistence.response.LoginResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
// @CrossOrigin(origins = { "http://localhost:5173", "http://localhost:80" })
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Welcome to the root.");
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest request) {
        User registeredUser = authenticationService.signup(request);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        User authenticatedUser = authenticationService.authenticate(request);

        String token = jwtService.generateToken(authenticatedUser);

        Cookie cookie = new Cookie("authToken", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/user")
    public ResponseEntity<User> authenticatedUser(
            @CookieValue(value = "authToken") String authToken) {
        if (authToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }
}
