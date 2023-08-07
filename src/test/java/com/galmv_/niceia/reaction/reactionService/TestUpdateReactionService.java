package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.domain.reaction.services.ReactionUpdateService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestUpdateReactionService extends UnitTestFactory {

    @Autowired
    private ReactionUpdateService reactionUpdateService;

    @Test
    @DisplayName("should to be able to update a reaction")
    public void TestSuccessUpdate(){
        this.reactionUpdateService.execute(reaction.getId(), Type.FUN);

        Assert.assertEquals(Type.FUN, reactionService.findById(reaction.getId()).getType());
    }

    @Test
    @DisplayName("should not to be able to update a reaction if this one not exists")
    public void TestFailUpdate(){
        Assert.assertThrows(ReactionNotFoundedException.class, () ->
                this.reactionUpdateService.execute(new UUID(0, 0), Type.CONGRATULATIONS));
    }
}
