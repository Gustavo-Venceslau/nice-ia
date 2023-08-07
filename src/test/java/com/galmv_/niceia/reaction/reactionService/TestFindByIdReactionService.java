package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.domain.reaction.services.FindByIdReactionService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestFindByIdReactionService extends UnitTestFactory {

    @Autowired
    private FindByIdReactionService findByIdReactionService;

    @Test
    @DisplayName("should to be possible to find a reaction by id")
    public void testSuccessFindById(){
        Assert.assertNotNull(this.findByIdReactionService.execute(reaction.getId()));
    }

    @Test
    @DisplayName("should not to be possible to find a reaction by id if this one don't exists")
    public void testFailFindById(){
        Assert.assertThrows(ReactionNotFoundedException.class, () -> this.findByIdReactionService.execute(new UUID(0, 0)));
    }
}
