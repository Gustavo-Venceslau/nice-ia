package com.galmv_.niceia.reaction.reactionController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.niceia.IntegrationTestFactory;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class TestUpdateReactionController extends IntegrationTestFactory {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("should to be able to update a reaction in /reaction/{id}")
    public void testSuccessUpdate() throws Exception{

        String reactionNewDataRequest = mapper.writeValueAsString(Type.LOVED);

        mockMvc.perform(MockMvcRequestBuilders.put("/reaction/{id}", reaction.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(reactionNewDataRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to be able to update a reaction in /reaction/{id} if this reaction don't exist")
    public void testFailUpdate() throws Exception{

        String reactionNewDataRequest = mapper.writeValueAsString(Type.LOVED);

        mockMvc.perform(MockMvcRequestBuilders.put("/reaction/{id}", new UUID(0, 0))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(reactionNewDataRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
