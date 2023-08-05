package com.galmv_.niceia.domain.post.controllers;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.services.FindAllByStudentPostService;
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
@RequestMapping("/post/student/{id}")
@RequiredArgsConstructor
@Slf4j
public class FindByStudentPostController {

    private final FindAllByStudentPostService findAllByStudentPostService;

    @GetMapping
    public ResponseEntity<List<Post>> handle(@PathVariable("id") UUID id){
        try {
            List<Post> postsByStudent = this.findAllByStudentPostService.execute(id);

            return ResponseEntity.ok().body(postsByStudent);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
