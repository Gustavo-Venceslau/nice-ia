package com.galmv_.niceia.domain.comment.controllers;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.dtos.CreateAndUpdateCommentDTO;
import com.galmv_.niceia.domain.comment.services.CommentCreateService;
import com.galmv_.niceia.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/comment/{id}")
@RequiredArgsConstructor
@Slf4j
public class CommentCreateController {

    private final CommentCreateService commentCreateService;

    @PostMapping
    public ResponseEntity<Comment> create
            (@RequestBody CreateAndUpdateCommentDTO textField, @PathVariable("id") UUID postId, @RequestHeader(name = "Authorization") String token){
        try {
            Comment comment = this.commentCreateService.execute(textField.text(), postId, token);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();

            return ResponseEntity.created(uri).body(comment);
        }
        catch (EntityNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
