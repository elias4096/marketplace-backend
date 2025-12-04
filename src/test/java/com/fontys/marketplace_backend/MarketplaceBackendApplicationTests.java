package com.fontys.marketplace_backend;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fontys.marketplace_backend.persistence.entity.Item;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MarketplaceBackendApplicationTests {
        @Autowired
        private MockMvc mockMvc;

        Item buildTestItem() {
                return Item.builder()
                                .id(null)
                                .sellerUserId(1)
                                .sellerDisplayName("Seller")
                                .title("Test item")
                                .description("Test item description")
                                .price(15.0)
                                .category("Electronics")
                                .location("Rotterdam")
                                .quality("New")
                                .createdAt(null)
                                .build();
        }

        @Test
        @WithMockUser
        void testGetItems() throws Exception {
                mockMvc.perform(get("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}"))
                                .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        void testPostItems() throws Exception {
                Item item = buildTestItem();

                String json = mockMvc.perform(post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(item)))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                Item result = new ObjectMapper()
                                .registerModule(new JavaTimeModule())
                                .readValue(json, Item.class);

                int id = result.getId();

                mockMvc.perform(get("/items/{itemId}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}"))
                                .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        void testDeleteItem() throws Exception {
                Item item = buildTestItem();

                String json = mockMvc.perform(post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(item)))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                Item result = new ObjectMapper()
                                .registerModule(new JavaTimeModule())
                                .readValue(json, Item.class);

                int id = result.getId();

                mockMvc.perform(delete("/user/items/{itemId}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}"))
                                .andExpect(status().isOk());

                mockMvc.perform(get("/items/{itemId}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}"))
                                .andExpect(status().is4xxClientError());
        }
}
