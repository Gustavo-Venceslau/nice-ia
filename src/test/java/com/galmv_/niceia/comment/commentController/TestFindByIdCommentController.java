package com.galmv_.niceia.comment.commentController;

import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestFindByIdCommentController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find a comment by id in /comment/{id}")
    public void testSuccessFindById() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/comment/{id}", comment.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(4))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find a comment by id in /comment/{id} if comment don't exist")
    public void testFailFindById() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/comment/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
