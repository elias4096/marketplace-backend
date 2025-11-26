package com.fontys.marketplace_backend;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fontys.marketplace_backend.persistence.entity.User;
import com.fontys.marketplace_backend.persistence.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MarketplaceBackendApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private static String bearerToken;

    @Test
    @Order(1)
    void testSignup() throws Exception {
        Optional<User> user = userRepository.findByEmail("test@example.com");
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }

        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"displayName\":\"test123\", \"email\":\"test@example.com\", \"password\":\"password123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testLogin() throws Exception {
        MvcResult result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andReturn();

        bearerToken = result.getResponse().getContentAsString();
    }

    @Test
    @Order(3)
    void testGetItems() throws Exception {
        mockMvc.perform(get("/items")
                // .header("Authorization", "Bearer " + bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }
}
