package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.domain.reaction.services.FindAllByPostReactionService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TestFindAllByPostReactionService extends UnitTestFactory {

    @Autowired
    private FindAllByPostReactionService findAllByPostReactionService;

    @Test
    @DisplayName("should to be able to find All reactions by post")
    public void testSuccessFindAllByPost(){
        this.reactionRepository.save(
                new Reaction(null, Type.CONGRATULATIONS, post, comment, student));

        System.out.println(post.getId());

        List<Reaction> reactions = this.findAllByPostReactionService.execute(post.getId());

        System.out.println(reactions);

        Assert.assertFalse(reactions.isEmpty());
    }

    @Test
    @DisplayName("should not to be able to find All reactions by content if post not exists")
    public void testFailFindAllByPost(){
        Assert.assertThrows(PostNotFoundedException.class, () ->
                this.findAllByPostReactionService.execute(new UUID(0, 0)));
    }
}
