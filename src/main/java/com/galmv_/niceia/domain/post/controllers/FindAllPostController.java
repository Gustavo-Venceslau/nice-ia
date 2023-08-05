package com.galmv_.niceia.domain.post.controllers;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.post.services.FindAllPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class FindAllPostController {

    private final FindAllPostService findAllPostService;

    @GetMapping
    public ResponseEntity<List<Post>> handle(){
        try {
            List<Post> posts = this.findAllPostService.execute();

            return ResponseEntity.ok().body(posts);
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
