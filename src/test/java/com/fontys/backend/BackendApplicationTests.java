package com.fontys.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BackendApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSignup() throws Exception {
        String userJson = "{\"email\":\"test@mail.com\", \"password\":\"123\"}";

        mockMvc.perform(post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User created with email: test@mail.com"));
    }

    @Test
    public void testLogin() throws Exception {
        String loginJson = "{\"username\":\"test@mail.com\", \"password\":\"123\"}";

        mockMvc.perform(post("/api/login")
                        .contentType("application/json")
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}
