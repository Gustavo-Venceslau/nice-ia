package com.galmv_.niceia.student.studentController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.testFactories.IntegrationTestFactory;
import com.galmv_.niceia.auth.AuthenticationRequest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestLoginStudentController extends IntegrationTestFactory {

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("should to be able to login if student is exists")
    public void testSuccessLogin() throws Exception {
        AuthenticationRequest studentCredentials = new AuthenticationRequest("gustavo@mail.com", "123");

        String studentCredentialsRequest = mapper.writeValueAsString(studentCredentials);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(studentCredentialsRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to login if student not exists")
    public void testFailLogin() throws Exception {
        AuthenticationRequest studentCredentials2 = new AuthenticationRequest("gustavo@mail.com", "12");

        String studentCredentialsRequestToFail = mapper.writeValueAsString(studentCredentials2);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(studentCredentialsRequestToFail)
                )
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }
}
