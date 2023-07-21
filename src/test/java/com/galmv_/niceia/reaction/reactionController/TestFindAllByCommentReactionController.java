package com.galmv_.niceia.reaction.reactionController;

import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestFindAllByCommentReactionController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find all reactions by comment in /reaction/comment/{id}")
    public void testSuccessFindAllByComment() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/comment/{id}", comment.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find all reactions by comment in /reaction/comment/{id} if comment not exists")
    public void testFailFindAllByComment() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/comment/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
