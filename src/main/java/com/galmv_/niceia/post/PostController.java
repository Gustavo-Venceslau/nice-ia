package com.galmv_.niceia.post;

import com.galmv_.niceia.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        try {
            List<Post> posts = this.postService.findAll();

            return ResponseEntity.ok().body(posts);
        }
        catch (PostNotFoundedException e){
            System.out.println("Exception: " + e);

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
            System.out.println("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostDTO post, @RequestHeader(name = "Authorization") String token){
        try {
            Post post1 = this.postService.create(post, token);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post1.getId()).toUri();

            return ResponseEntity.created(uri).body(post1);
        }
        catch (UserNotFoundException e){
            System.out.println("Exception: " + e);

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
            System.out.println("Exception: " + e);

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
            System.out.println("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
