package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.shared.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestFindByIdReactionService extends UnitTestFactory {

    @Test
    @DisplayName("should to be possible to find a reaction by id")
    public void testSuccessFindById(){
        Assert.assertNotNull(this.reactionService.findById(reaction.getId()));
    }

    @Test
    @DisplayName("should not to be possible to find a reaction by id if this one don't exists")
    public void testFailFindById(){
        Assert.assertThrows(ReactionNotFoundedException.class, () -> this.reactionService.findById(new UUID(0, 0)));
    }
}
