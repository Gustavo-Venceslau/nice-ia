package com.galmv_.niceia.reaction.reactionController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.IntegrationTestFactory;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestCreateReactionController extends IntegrationTestFactory {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("should to be able to create a reaction in /reaction")
    public void testSuccessCreate() throws Exception{
        Reaction newReaction = Reaction
                .builder()
                .type(Type.LOVED)
                .post(post)
                .comment(comment)
                .student(student)
                .build();

        String newReactionRequest = mapper.writeValueAsString(newReaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/reaction")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(newReactionRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(Type.LOVED))
                .andDo(MockMvcResultHandlers.print());
    }
}
