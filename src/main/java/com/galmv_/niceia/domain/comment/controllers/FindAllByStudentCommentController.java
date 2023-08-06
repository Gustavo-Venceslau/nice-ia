package com.galmv_.niceia.domain.comment.controllers;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.services.FindAllByStudentCommentService;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
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
@RequestMapping("/comment/student/{id}")
@RequiredArgsConstructor
@Slf4j
public class FindAllByStudentCommentController {

    private final FindAllByStudentCommentService findAllByStudentCommentService;

    @GetMapping
    public ResponseEntity<List<Comment>> handle(@PathVariable("id") UUID id){
        try {
            List<Comment> comments = this.findAllByStudentCommentService.execute(id);

            return ResponseEntity.ok().body(comments);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
