package com.galmv_.niceia.reaction.reactionController;

import com.galmv_.niceia.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestFindByIdReactionController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find a reaction by id in /reaction/{id}")
    public void testSuccessFindById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/{id}", reaction.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find a reaction by id in /reaction/{id} if this one don't exists")
    public void testFailFindById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
