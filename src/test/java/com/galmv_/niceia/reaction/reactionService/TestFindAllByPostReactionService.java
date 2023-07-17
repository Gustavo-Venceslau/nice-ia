package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.UnitTestFactory;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.UUID;

public class TestFindAllByPostReactionService extends UnitTestFactory {

    @Test
    @DisplayName("should to be able to find All reactions by post")
    public void testSuccessFindAllByPost(){
        this.reactionRepository.save(
                new Reaction(null, Type.CONGRATULATIONS, post, comment, student));

        System.out.println(post.getId());

        List<Reaction> reactions = this.reactionService.findAllByPost(post.getId());

        System.out.println(reactions);

        Assert.assertFalse(reactions.isEmpty());
    }

    @Test
    @DisplayName("should not to be able to find All reactions by content if post not exists")
    public void testFailFindAllByPost(){
        Assert.assertThrows(PostNotFoundedException.class, () ->
                this.reactionService.findAllByPost(new UUID(0, 0)));
    }
}
