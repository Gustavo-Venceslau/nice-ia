package com.galmv_.niceia.post.postController;

import com.galmv_.niceia.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestFindAllPostController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find all posts in /post")
    public void testSuccessFindAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/post"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find all posts in /post if they don't exists")
    public void testFailFindAll() throws Exception{
        reactionRepository.deleteAll();
        commentRepository.deleteAll();
        postRepository.deleteAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/post"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
