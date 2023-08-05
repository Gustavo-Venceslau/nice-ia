package com.galmv_.niceia.post.postController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.domain.post.PostDTO;
import com.galmv_.niceia.testFactories.IntegrationTestFactory;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestCreatePostController extends IntegrationTestFactory {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("should to be able to create a user in /post")
    public void testSuccessCreate() throws Exception{

        Student student1 = studentRepository.save(Student.builder()
                .firstName("gustavo")
                .lastName("vence")
                .email("gustavo7@mail.com")
                .password("1237")
                .studentRole(StudentRole.USER)
                .build());

        PostDTO postToCreate = new PostDTO("new post", "URL", student1.getId());

        String postRequest = mapper.writeValueAsString(postToCreate);

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(postRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(4))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to create a user in /post if student not exists")
    public void testFailCreate() throws Exception{

        Student student1 = Student.builder()
                .firstName("gustavo")
                .lastName("vence")
                .email("gustavo6@mail.com")
                .password("1237")
                .studentRole(StudentRole.USER)
                .build();

        PostDTO postToCreate = new PostDTO("new post", "URL", new UUID(0, 0));

        String postRequest = mapper.writeValueAsString(postToCreate);

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(postRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
