package com.galmv_.niceia.comment.commentController;

import com.galmv_.niceia.IntegrationTestFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestFindAllCommentController extends IntegrationTestFactory {

    @Test
    @DisplayName("Should to be able to find all comment in /comment")
    public void testSuccessFindAll() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/comment"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(4))
                .andDo(MockMvcResultHandlers.print());
    }
}
