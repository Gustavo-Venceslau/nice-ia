package com.galmv_.niceia.student.studentController;

import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

public class TestFindByIdStudentController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find a by id in the url /student/{id}")
    public void testSuccessFindById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", student.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(student.getEmail()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find a by id in the url /student/{id} if user don't exists")
    public void testFailFindById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
