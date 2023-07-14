package com.galmv_.niceia.post.postController;

import com.galmv_.niceia.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestFindByIdPostController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find by id in /post/{id}")
    public void testSuccessFindById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/post/{id}", post.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(4))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find by id in /post/{id} if post don't exists")
    public void testFailFindById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/post/{id}", new UUID(0 , 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
