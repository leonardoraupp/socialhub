package com.example.mongodb_springboot.services;

import com.example.mongodb_springboot.domain.Post;
import com.example.mongodb_springboot.exceptions.ObjectNotFoundException;
import com.example.mongodb_springboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitle(text);
    }

    public List<Post> findByMultipleFields(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);  // add 24h(1 day) to maxDate.
        return repository.findByMultipleFields(text, minDate, maxDate);
    }
}
