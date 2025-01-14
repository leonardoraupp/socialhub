package com.example.mongodb_springboot.resources;

import com.example.mongodb_springboot.domain.Post;
import com.example.mongodb_springboot.resources.util.URL;
import com.example.mongodb_springboot.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post object = postService.findById(id);
        return ResponseEntity.ok().body(object);
    }

    @GetMapping(value = "/titleSearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam String text) {
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }
}