package com.galmv_.niceia.reaction.reactionController;

import com.galmv_.niceia.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestFindAllByStudentReactionController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find all by student in /reaction/student/{id}")
    public void testSuccessFindByStudent() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/student/{id}", student.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find all by student in /reaction/student/{id} if student don't exists ")
    public void testFailFindByStudent() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/student/{id}", new UUID(0 ,0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
