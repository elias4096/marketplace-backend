package com.fontys.marketplace_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.marketplace_backend.persistence.entity.Item;
import com.fontys.marketplace_backend.persistence.repository.ItemRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class MarketplaceBackendApplicationTests {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ItemRepository itemRepository;

        Item buildTestItem() {
                return Item.builder()
                                .sellerUserId(1)
                                .sellerDisplayName("Test seller")
                                .title("Test item title")
                                .description("Test item description")
                                .price(8.0)
                                .category("Electronics")
                                .quality("New")
                                .location("Rotterdam")
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
                Item testItem = buildTestItem();
                testItem.setSellerDisplayName("testPostItems");

                mockMvc.perform(post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(testItem)))
                                .andExpect(status().isOk());

                boolean exists = itemRepository.findAllBySellerDisplayName("testPostItems").iterator().hasNext();
                assertTrue(exists, "Item should be saved and retrievable by seller display name");
        }

        @Test
        @WithMockUser
        void testDeleteItem() throws Exception {
                Item testItem = buildTestItem();

                Item item = itemRepository.save(testItem);

                mockMvc.perform(delete("/user/items/{itemId}", item.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());

                boolean exists = itemRepository.existsById(item.getId());
                assertFalse(exists, "Item should be deleted and no longer exist in the repository");
        }
}
