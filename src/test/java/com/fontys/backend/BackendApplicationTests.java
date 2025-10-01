package com.fontys.backend;

import com.fontys.backend.request.SignupRequest;
import com.fontys.backend.service.JwtService;
import com.fontys.backend.service.SimpleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BackendApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimpleService simpleService;

    @Test
    public void testGreet() {
        String result = simpleService.greet("World");
        assertEquals("Hello, World!", result);
    }

    @Test
    public void testSignup_EmailAlreadyInUse() throws Exception {
        SignupRequest request = new SignupRequest("test@example.com", "Test User", "password");

        mockMvc.perform(post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\", \"displayName\":\"Test User\", \"password\":\"password\"}"))
                .andExpect(status().isOk());
    }
}
