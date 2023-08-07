package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.domain.reaction.services.FindAllByCommentReactionService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.reaction.Reaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TestFindAllByCommentReactionService extends UnitTestFactory {

    @Autowired
    private FindAllByCommentReactionService findAllByCommentReactionService;

    @Test
    @DisplayName("should to be able to find all by comment")
    public void testSuccessFindAllByComment(){
        List<Reaction> reactions = this.findAllByCommentReactionService.execute(comment.getId());

        Assert.assertFalse(reactions.isEmpty());
    }

    @Test
    @DisplayName("should not to be able to find all by comment if comment not exists")
    public void testFailFindAllByComment(){
        Assert.assertThrows(CommentNotFoundException.class, () ->
                this.findAllByCommentReactionService.execute(new UUID(0, 0)));
    }
}
