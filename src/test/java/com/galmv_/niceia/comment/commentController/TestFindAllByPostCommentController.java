package com.galmv_.niceia.comment.commentController;

import com.galmv_.niceia.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestFindAllByPostCommentController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find all comments by post in /comment/post/{id}")
    public void testSuccessFindAllByPost() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/comment/post/{id}", post.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find all comments by post in /comment/post/{id} if post don't exists")
    public void testFailFindAllByPost() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/comment/post/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
