package com.galmv_.niceia.reaction.reactionController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.shared.testFactories.IntegrationTestFactory;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.ReactionDTO;
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
        ReactionDTO newReaction = new ReactionDTO(Type.LOVED, post.getId(), comment.getId(),student.getId());

        String newReactionRequest = mapper.writeValueAsString(newReaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/reaction")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(newReactionRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("LOVED"))
                .andDo(MockMvcResultHandlers.print());

        this.reactionRepository.deleteAll();
    }
}
