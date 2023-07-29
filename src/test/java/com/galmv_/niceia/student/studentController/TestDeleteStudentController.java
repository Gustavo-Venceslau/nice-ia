package com.galmv_.niceia.student.studentController;

import com.galmv_.niceia.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestDeleteStudentController extends IntegrationTestFactory {


    @Test
    @DisplayName("should to be able to delete a student in /student/{id}")
    public void testSuccessDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}", student.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to delete a student in /student/{id} if this one don't exits")
    public void testFailDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
