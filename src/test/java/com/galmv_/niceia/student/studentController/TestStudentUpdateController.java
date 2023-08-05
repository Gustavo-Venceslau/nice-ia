package com.galmv_.niceia.student.studentController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.testFactories.IntegrationTestFactory;
import com.galmv_.niceia.domain.student.StudentDTO;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestStudentUpdateController extends IntegrationTestFactory {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("should to be able to update a user in /student/{id}")
    public void testSuccessUpdate() throws Exception{
        StudentDTO studentNewData = new StudentDTO("Gu", "Almeida", "gustavo@mail.com", "123");

        String studentNewDataRequest = mapper.writeValueAsString(studentNewData);

        mockMvc.perform(MockMvcRequestBuilders.put("/student/{id}", student.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(studentNewDataRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to update a user in /student/{id} if this one don't exist")
    public void testFailUpdate() throws Exception{
        StudentDTO studentNewData = new StudentDTO("Gu", "Almeida", "gustavo@mail.com", "123");

        String studentNewDataRequest = mapper.writeValueAsString(studentNewData);

        mockMvc.perform(MockMvcRequestBuilders.put("/student/{id}", new UUID(0, 0))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(studentNewDataRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
