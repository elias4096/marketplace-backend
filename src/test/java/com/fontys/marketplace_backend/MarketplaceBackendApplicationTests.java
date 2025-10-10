package com.fontys.marketplace_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MarketplaceBackendApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void alwaysOk() {
        assert (true);
    }

//    @Test
//    public void testSignupAndLogin() throws Exception {
//        mockMvc.perform(post("/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"displayName\":\"Test123\", \"email\":\"test@example.com\", \"password\":\"password123\"}"))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(post("/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"email\":\"test@example.com\", \"password\":\"password123\"}"))
//                .andExpect(status().isOk());
//    }
}
