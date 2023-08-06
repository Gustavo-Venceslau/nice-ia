package com.galmv_.niceia.domain.comment.controllers;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.comment.services.FindByIdCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment/{id}")
@Slf4j
public class FindByIdCommentController {

    private final FindByIdCommentService findByIdCommentService;

    @GetMapping
    public ResponseEntity<Comment> handle(@PathVariable("id") UUID id){
        try {
            Comment comment = this.findByIdCommentService.execute(id);

            return ResponseEntity.ok().body(comment);
        }
        catch (CommentNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
