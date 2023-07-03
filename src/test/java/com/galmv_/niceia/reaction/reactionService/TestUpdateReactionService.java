package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.reaction.Enums.Type;
import com.galmv_.niceia.reaction.exceptions.ReactionNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestUpdateReactionService extends TestFactory {

    @Test
    @DisplayName("should to be able to update a reaction")
    public void TestSuccessUpdate(){
        this.reactionService.update(reaction.getId(), Type.FUN);

        Assert.assertEquals(Type.FUN, reactionService.findById(reaction.getId()).getType());
    }

    @Test
    @DisplayName("should not to be able to update a reaction if this one not exists")
    public void TestFailUpdate(){
        Assert.assertThrows(ReactionNotFoundedException.class, () ->
                this.reactionService.update(new UUID(0, 0), Type.CONGRATULATIONS));
    }
}
