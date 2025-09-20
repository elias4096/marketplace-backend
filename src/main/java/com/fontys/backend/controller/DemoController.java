package com.fontys.backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to this public endpoint.";
    }

    @GetMapping("/api/user")
    public String user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Private endpoint, you are logged in as: "
                + auth.getName() + ", "
                + auth.getPrincipal().toString() + ", "
                + auth.getAuthorities().toString() + ", "
                + auth.getDetails();

        // elias@mail.com, User(id=1, email=elias@mail.com, password=$2a$10$HaLRhB6j6/jUiRZ1wNmBfek82vBGvjJmCBm1jgaOtoLGTqYw5mytu, role=USER), [USER], WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null]
    }
}
