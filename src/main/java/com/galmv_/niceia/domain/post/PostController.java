package com.galmv_.niceia.domain.post;

import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        try {
            List<Post> posts = this.postService.findAll();

            return ResponseEntity.ok().body(posts);
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable("id") UUID id){
        try {
            Post post = postService.findById(id);

            return ResponseEntity.ok().body(post);
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<List<Post>> findAllByStudent(@PathVariable("id") UUID id){
        try {
            List<Post> postsByStudent = this.postService.findAllByStudent(id);

            return ResponseEntity.ok().body(postsByStudent);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostDTO post){
        try {
            Post post1 = this.postService.create(post);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post1.getId()).toUri();

            return ResponseEntity.created(uri).body(post1);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody PostDTO postNewData){
        try {
            this.postService.update(id, postNewData);

            return ResponseEntity.noContent().build();
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        try {
            this.postService.delete(id);

            return ResponseEntity.noContent().build();
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
