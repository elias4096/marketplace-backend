package com.fontys.marketplace_backend;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void uploadAndRetrieveImage() throws Exception {
        String itemId = "0";
        byte[] data = "hello-image".getBytes();

        MockMultipartFile multipartFile = new MockMultipartFile("image", "test.png", "image/png", data);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/images")
                .file(multipartFile)
                .param("itemId", itemId))
                .andExpect(status().isOk())
                .andExpect(content().string("Image uploaded successfully"));

        MvcResult getResult = mockMvc.perform(get("/images/" + itemId + ".png"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andReturn();

        byte[] returned = getResult.getResponse().getContentAsByteArray();
        assertArrayEquals(data, returned);

        Path p = Paths.get(System.getProperty("user.dir"), "images", itemId + ".png");
        Files.deleteIfExists(p);
    }
}
