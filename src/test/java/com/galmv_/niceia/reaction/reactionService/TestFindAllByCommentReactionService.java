package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.shared.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.reaction.Reaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.UUID;

public class TestFindAllByCommentReactionService extends UnitTestFactory {

    @Test
    @DisplayName("should to be able to find all by comment")
    public void testSuccessFindAllByComment(){
        List<Reaction> reactions = this.reactionService.findAllByComment(comment.getId());

        Assert.assertFalse(reactions.isEmpty());
    }

    @Test
    @DisplayName("should not to be able to find all by comment if comment not exists")
    public void testFailFindAllByComment(){
        Assert.assertThrows(CommentNotFoundException.class, () ->
                this.reactionService.findAllByComment(new UUID(0, 0)));
    }
}
