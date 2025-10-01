package com.fontys.backend.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}