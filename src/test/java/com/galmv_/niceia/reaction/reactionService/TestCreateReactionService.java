package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.UnitTestFactory;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestCreateReactionService extends UnitTestFactory {

    @Test
    @DisplayName("should to be possible to create a new reaction")
    public void testSuccessCreate(){
        Reaction reaction1 = this.reactionService.create(new ReactionDTO(Type.LOVED, post, comment, student));

        Assert.assertNotNull(reactionService.findById(reaction1.getId()));
    }

    @Test
    @DisplayName("should not to be able to create a reaction if post not exist")
    public void testFailCreate(){
        Post newPost = new Post(new UUID(0, 0), "Good Afternoon", "", student);

        Assert.assertThrows(PostNotFoundedException.class, () ->
                this.reactionService.create(new ReactionDTO(Type.LOVED, newPost, comment, student)));
    }
}
