package com.example.mongodb_springboot.config;

import com.example.mongodb_springboot.domain.Post;
import com.example.mongodb_springboot.domain.User;
import com.example.mongodb_springboot.dto.AuthorDTO;
import com.example.mongodb_springboot.dto.CommentDTO;
import com.example.mongodb_springboot.repository.PostRepository;
import com.example.mongodb_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User leonardo = new User(null, "Leonardo", "leonardo@gmail.com");
        User ayanokoji = new User(null, "Ayanokoji", "ayanokoji@gmail.com");
        User horika = new User(null, "Horika", "horika@gmail.com");

        userRepository.saveAll(Arrays.asList(leonardo, ayanokoji, horika));

        Post post = new Post(null, format.parse("2025/01/08"), "My first post", "Hello everyone! I'm from from Brazil. Nice to meet you.", new AuthorDTO(leonardo));
        Post secondPost = new Post(null, format.parse("2025/01/08"), "Sunday", "Today I'm studying programming.", new AuthorDTO(leonardo));

        CommentDTO comment = new CommentDTO("I appreciate it.", format.parse("2025/01/12"), new AuthorDTO(ayanokoji));
        CommentDTO comment1 = new CommentDTO("Thank you, @ayanokoji", format.parse("2025/01/12"), new AuthorDTO(leonardo));
        CommentDTO secondPostComment = new CommentDTO("I'm also studying Java", format.parse("2025/01/13"), new AuthorDTO(horika));
        CommentDTO secondPostComment1 = new CommentDTO("That's nice, let's be friends!", format.parse("2025/01/13"), new AuthorDTO(leonardo));

        post.addComments(comment);
        post.addComments(comment1);
        secondPost.addComments(secondPostComment);
        secondPost.addComments(secondPostComment1);

        postRepository.saveAll(Arrays.asList(post, secondPost));

        leonardo.addPost(post);
        leonardo.addPost(secondPost);
        userRepository.save(leonardo);
    }
}