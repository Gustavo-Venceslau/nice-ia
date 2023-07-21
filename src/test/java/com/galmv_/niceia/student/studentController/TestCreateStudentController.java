package com.galmv_.niceia.student.studentController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import com.galmv_.niceia.auth.RegisterRequest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestCreateStudentController extends IntegrationTestFactory {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("should to be able to create a new student in /register")
    public void testSuccessCreate() throws Exception {
        RegisterRequest newStudent = new RegisterRequest("Isabelle", "Venceslau", "isabelle@mail.com", "321");

        String studentRequest = objectMapper.writeValueAsString(newStudent);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(studentRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to create a new student if this already exists")
    public void testFailCreate() throws Exception {
        RegisterRequest newStudent = new RegisterRequest("Isabelle", "Venceslau", "gustavodealmeida@gmail.com", "321");

        String studentRequest = objectMapper.writeValueAsString(newStudent);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(studentRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
