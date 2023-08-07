package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.domain.reaction.services.ReactionCreateService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestCreateReactionService extends UnitTestFactory {

    @Autowired
    private ReactionCreateService reactionCreateService;

    @Test
    @DisplayName("should to be possible to create a new reaction")
    public void testSuccessCreate(){
        Reaction reaction1 = this.reactionCreateService.execute(new ReactionDTO(Type.LOVED, post.getId(), comment.getId(), student.getId()));

        Assert.assertNotNull(reactionService.findById(reaction1.getId()));
    }

    @Test
    @DisplayName("should not to be able to create a reaction if post not exist")
    public void testFailCreate(){
        Post newPost = new Post(new UUID(0, 0), "Good Afternoon", "", student);

        Assert.assertThrows(PostNotFoundedException.class, () ->
                this.reactionCreateService.execute(new ReactionDTO(Type.LOVED, newPost.getId(), comment.getId(), student.getId())));
    }
}
