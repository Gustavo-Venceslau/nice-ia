package com.galmv_.niceia.comment.commentController;

import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import com.galmv_.niceia.domain.comment.Comment;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestDeleteCommentController extends IntegrationTestFactory {

    @Test
    @DisplayName("should to be able to delete a user in /comment/{id}")
    public void testSuccessDelete() throws Exception{
        Comment commentToDelete = this.commentRepository.save(Comment
                .builder()
                .text("Hello guys!")
                .student(student)
                .post(post)
                .build()
        );

        mockMvc.perform(MockMvcRequestBuilders.delete("/comment/{id}", commentToDelete.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to delete a user in /comment/{id} if this one don't exists")
    public void testFailDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/comment/{id}", new UUID(0, 0)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
