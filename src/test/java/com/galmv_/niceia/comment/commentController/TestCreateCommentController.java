package com.galmv_.niceia.comment.commentController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.comment.dtos.CommentDTO;
import com.galmv_.niceia.domain.comment.dtos.CreateAndUpdateCommentDTO;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestCreateCommentController extends IntegrationTestFactory {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("should to be able to create a comment in /comment/{id}")
    public void testSuccessCreate() throws Exception{
        String token = jwtService.generateToken(student);

        CreateAndUpdateCommentDTO newComment = new CreateAndUpdateCommentDTO("good one guys!");
        String newCommentRequest = mapper.writeValueAsString(newComment);

        mockMvc.perform(MockMvcRequestBuilders.post("/comment/{id}", post.getId())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(newCommentRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("good one guys!"))
                .andDo(MockMvcResultHandlers.print());

        reactionRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("should not to be able to create a comment in /comment/{id} if post not exists")
    public void testFailCreate() throws Exception{
        CreateAndUpdateCommentDTO newComment = new CreateAndUpdateCommentDTO("good one guys");

        String token = jwtService.generateToken(student);

        String newCommentRequest = mapper.writeValueAsString(newComment);

        mockMvc.perform(MockMvcRequestBuilders.post("/comment/{id}", UUID.randomUUID())
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(newCommentRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to create a comment in /comment/{id} if student don't exists")
    public void testFailCreateWithWrongToken() throws Exception{
        CommentDTO newComment = new CommentDTO("good one guys", post, student);

        String token = jwtService.generateToken(
                new Student(null, "bella", "bella", "bella@mail.com", "123", StudentRole.USER));

        String newCommentRequest = mapper.writeValueAsString(newComment);

        mockMvc.perform(MockMvcRequestBuilders.post("/comment/{id}", post.getId(), token)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(newCommentRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}


