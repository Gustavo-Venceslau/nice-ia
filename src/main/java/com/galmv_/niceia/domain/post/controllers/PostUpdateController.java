package com.galmv_.niceia.domain.post.controllers;

import com.galmv_.niceia.domain.post.PostDTO;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.post.services.PostUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post/{id}")
@RequiredArgsConstructor
@Slf4j
public class PostUpdateController {

    private final PostUpdateService postUpdateService;

    @PutMapping
    public ResponseEntity<Void> handle(@PathVariable("id") UUID id, @RequestBody PostDTO postNewData){
        try {
            this.postUpdateService.execute(id, postNewData);

            return ResponseEntity.noContent().build();
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
