package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.reaction.Enums.ComponentType;
import com.galmv_.niceia.reaction.Enums.Type;
import com.galmv_.niceia.reaction.Reaction;
import com.galmv_.niceia.reaction.ReactionDTO;
import com.galmv_.niceia.reaction.exceptions.ComponentReactionNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestCreateReactionService extends TestFactory {

    @Test
    @DisplayName("should to be possible to create a new reaction")
    public void testSuccessCreate(){
        Reaction reaction1 = this.reactionService.create(new ReactionDTO(Type.LOVED, ComponentType.COMMENT, comment.getId(), student));

        Assert.assertNotNull(reactionService.findById(reaction1.getId()));
    }

    @Test
    @DisplayName("should not to be possible to create a new reaction if component reaction don't exists")
    public void testFailCreate(){
        Assert.assertThrows(ComponentReactionNotFoundedException.class, () ->
                this.reactionService.create(new ReactionDTO(Type.LOVED, ComponentType.COMMENT, new UUID(0, 0), student)));
    }
}
