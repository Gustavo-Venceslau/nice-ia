package com.galmv_.niceia.reaction.reactionController;

import com.galmv_.niceia.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestDeleteReactionController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to delete a reaction in /reaction/{id}")
    public void testSuccessDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/reaction/{id}", reaction.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to delete a reaction in /reaction/{id} if reaction don't exist")
    public void testFailDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/reaction/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
