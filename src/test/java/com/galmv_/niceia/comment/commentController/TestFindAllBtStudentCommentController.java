package com.galmv_.niceia.comment.commentController;

import com.galmv_.niceia.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestFindAllBtStudentCommentController extends IntegrationTestFactory {

    @Test
    @DisplayName("Should to be able to find all comment in /comment/student/{id}")
    public void testSuccessFindAll() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/comment/student/{id}", student.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Should not to be able to find all comment in /comment/student/{id} if this student don't exists")
    public void testFailFindAll() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/comment/student/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
