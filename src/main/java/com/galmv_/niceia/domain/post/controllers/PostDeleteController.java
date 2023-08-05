package com.galmv_.niceia.domain.post.controllers;

import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.post.services.PostDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/post/{id}")
@RequiredArgsConstructor
@Slf4j
public class PostDeleteController {

    private final PostDeleteService postDeleteService;

    @DeleteMapping
    public ResponseEntity<Void> handle(@PathVariable("id") UUID id){
        try {
            this.postDeleteService.execute(id);

            return ResponseEntity.noContent().build();
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
