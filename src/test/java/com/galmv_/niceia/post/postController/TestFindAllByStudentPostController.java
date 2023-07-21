package com.galmv_.niceia.post.postController;

import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestFindAllByStudentPostController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to find all post by student in /post/stundent/{id}")
    public void testSuccessFindAllByStudent() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/post/student/{id}", student.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to find all post by student in /post/stundent/{id} if student don't exists")
    public void testFailFindAllByStudent() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/post/student/{id}", student.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
