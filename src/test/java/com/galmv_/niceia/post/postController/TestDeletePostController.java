package com.galmv_.niceia.post.postController;

import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestDeletePostController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to delete a user in /post/{id}")
    public void testSuccessDelete() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/post/{id}", post.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        Assert.assertTrue(postRepository.findById(post.getId()).isEmpty());
    }

    @Test
    @DisplayName("should to be able to delete a user in /post/{id}")
    public void testFailDelete() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/post/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
