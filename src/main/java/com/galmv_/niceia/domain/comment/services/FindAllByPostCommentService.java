package com.galmv_.niceia.domain.comment.services;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.services.FindByIdPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllByPostCommentService {

    private final CommentRepository repository;
    private final FindByIdPostService findByIdPostService;

    public List<Comment> execute(UUID postId){
        Post post = this.findByIdPostService.execute(postId);

        return this.repository.findAllByPost(post);
    }
}
