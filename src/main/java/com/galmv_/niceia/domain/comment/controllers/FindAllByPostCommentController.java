package com.galmv_.niceia.domain.comment.controllers;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.services.FindAllByPostCommentService;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment/post/{id}")
@RequiredArgsConstructor
@Slf4j
public class FindAllByPostCommentController {

    private final FindAllByPostCommentService findAllByPostCommentService;

    @GetMapping
    public ResponseEntity<List<Comment>> handle(@PathVariable("id") UUID postId){
        try {
            List<Comment> comments = this.findAllByPostCommentService.execute(postId);

            return ResponseEntity.ok().body(comments);
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
