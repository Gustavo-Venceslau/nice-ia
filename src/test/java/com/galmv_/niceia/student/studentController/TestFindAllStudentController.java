package com.galmv_.niceia.student.studentController;

import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestFindAllStudentController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be possible to find All at the /student url")
    public void testSuccessFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
