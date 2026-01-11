package com.fontys.marketplace_backend;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.marketplace_backend.persistence.repository.UserRepository;
import com.fontys.marketplace_backend.persistence.requests.LoginRequest;
import com.fontys.marketplace_backend.persistence.requests.SignupRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @Test
    void root() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is a test endpoint."));
    }

    @Test
    void post_ShouldAuthenticateUser() throws Exception {
        // Signup

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setDisplayName("test");
        signupRequest.setEmail("testone@mail.com");
        signupRequest.setPassword("123");

        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signupRequest)))
                .andExpect(status().isOk());

        boolean exists = repository.findByEmail("testone@mail.com").isPresent();
        assertTrue(exists, "User should be saved and retrievable by email");

        // Login

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("testone@mail.com");
        loginRequest.setPassword("123");

        var loginResult = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        // Current user

        String responseBody = loginResult.getResponse().getContentAsString();
        String token = new ObjectMapper().readTree(responseBody).get("token").asText();

        mockMvc.perform(get("/user")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("testone@mail.com")));
    }
}
