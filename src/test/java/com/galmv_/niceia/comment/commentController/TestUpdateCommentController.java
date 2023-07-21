package com.galmv_.niceia.comment.commentController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.IntegrationTestFactory;
import com.galmv_.niceia.domain.comment.dtos.CreateAndUpdateCommentDTO;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestUpdateCommentController extends IntegrationTestFactory {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("should to be possible to update a comment in /comment/{id}")
    public void testSuccessUpdate() throws Exception{
        CreateAndUpdateCommentDTO commentNewMessage = new CreateAndUpdateCommentDTO("Good Morning");

        String commentNewMessageRequest = mapper.writeValueAsString(commentNewMessage);

        mockMvc.perform(MockMvcRequestBuilders.put("/comment/{id}", comment.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(commentNewMessageRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be possible to update a comment in /comment/{id} if this one don't exists")
    public void testFailUpdate() throws Exception{
        CreateAndUpdateCommentDTO commentNewMessage = new CreateAndUpdateCommentDTO("Good Morning");

        String commentNewMessageRequest = mapper.writeValueAsString(commentNewMessage);

        mockMvc.perform(MockMvcRequestBuilders.put("/comment/{id}", new UUID(0, 0))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(commentNewMessageRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
