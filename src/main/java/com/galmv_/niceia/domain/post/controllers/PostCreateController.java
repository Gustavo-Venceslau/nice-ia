package com.galmv_.niceia.domain.post.controllers;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostDTO;
import com.galmv_.niceia.domain.post.services.PostCreateService;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostCreateController {

    private final PostCreateService postCreateService;

    @PostMapping
    public ResponseEntity<Post> handle(@RequestBody PostDTO post){
        try {
            Post post1 = this.postCreateService.execute(post);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post1.getId()).toUri();

            return ResponseEntity.created(uri).body(post1);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
