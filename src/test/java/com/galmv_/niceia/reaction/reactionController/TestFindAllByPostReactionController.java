package com.galmv_.niceia.reaction.reactionController;

import com.galmv_.niceia.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestFindAllByPostReactionController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find All By Content type in /reaction/post/{id}")
    public void testSuccessFindAllByPost() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/post/{id}", reaction.getPost().getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find All By Content type in /reaction/post/{id} if component not exists")
    public void testFailFindAllByPost() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/component/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
