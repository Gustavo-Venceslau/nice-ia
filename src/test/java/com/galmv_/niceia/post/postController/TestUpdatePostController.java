package com.galmv_.niceia.post.postController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.IntegrationTestFactory;
import com.galmv_.niceia.domain.post.PostDTO;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestUpdatePostController extends IntegrationTestFactory {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("should to be able to update a post in /post/{id}")
    public void testSuccessUpdate() throws Exception{
        PostDTO newPostData = new PostDTO("good morning guys", "url do cachorro");

        String newPostDataRequest = mapper.writeValueAsString(newPostData);

        mockMvc.perform(MockMvcRequestBuilders.put("/post/{id}", post.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(newPostDataRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to update a post in /post/{id} if post not exists")
    public void testFailUpdate() throws Exception{
        PostDTO newPostData = new PostDTO("good morning guys", "url do cachorro");

        String newPostDataRequest = mapper.writeValueAsString(newPostData);

        mockMvc.perform(MockMvcRequestBuilders.put("/post/{id}", new UUID(0 , 0))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(newPostDataRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
