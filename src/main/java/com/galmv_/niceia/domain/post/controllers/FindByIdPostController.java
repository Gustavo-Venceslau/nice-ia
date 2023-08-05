package com.galmv_.niceia.domain.post.controllers;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.post.services.FindByIdPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/post/{id}")
@RequiredArgsConstructor
@Slf4j
public class FindByIdPostController {

    private final FindByIdPostService findByIdPostService;

    @GetMapping
    public ResponseEntity<Post> handle(@PathVariable("id") UUID id){
        try {
            Post post = findByIdPostService.execute(id);

            return ResponseEntity.ok().body(post);
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
