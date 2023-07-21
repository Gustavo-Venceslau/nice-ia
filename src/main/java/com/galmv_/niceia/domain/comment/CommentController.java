package com.galmv_.niceia.domain.comment;

import com.galmv_.niceia.domain.comment.dtos.CreateAndUpdateCommentDTO;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import com.galmv_.niceia.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService service;

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<List<Comment>> findAllByStudent(@PathVariable("id") UUID id){
        try {
            List<Comment> comments = this.service.findAllByStudent(id);

            return ResponseEntity.ok().body(comments);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> findById(@PathVariable("id") UUID id){
        try {
            Comment comment = this.service.findById(id);

            return ResponseEntity.ok().body(comment);
        }
        catch (CommentNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Comment> create
            (@RequestBody CreateAndUpdateCommentDTO textField, @PathVariable("id") UUID postId, @RequestHeader(name = "Authorization") String token){
        try {
            Comment comment = this.service.create(textField.text(), postId, token);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();

            return ResponseEntity.created(uri).body(comment);
        }
        catch (EntityNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody CreateAndUpdateCommentDTO newData){
        try {
            this.service.update(id, newData);

            return ResponseEntity.noContent().build();
        }
        catch (CommentNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        try {
            this.service.delete(id);

            return ResponseEntity.noContent().build();
        }
        catch (CommentNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
