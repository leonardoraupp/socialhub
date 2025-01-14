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

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User leonardo = new User(null, "Leonardo", "leonardo@gmail.com");
        User aynokoji = new User(null, "Aynokoji", "aynokoji@gmail.com");
        User horika = new User(null, "Horika", "horika@gmail.com");

        userRepository.saveAll(Arrays.asList(leonardo, aynokoji, horika));

        Post post = new Post(null, format.parse("12/01/2024"), "My first post", "Hello everyone! I'm from from Brazil. Nice to meet you.", new AuthorDTO(leonardo));
        Post secondPost = new Post(null, format.parse("13/01/2024"), "Sunday", "Today I'm studying programming.", new AuthorDTO(leonardo));

        CommentDTO comment = new CommentDTO("I appreciate it.", format.parse("12/01/2024"), new AuthorDTO(aynokoji));
        CommentDTO comment1 = new CommentDTO("Thank you, @aynokoji", format.parse("12/01/2024"), new AuthorDTO(leonardo));
        CommentDTO secondPostComment = new CommentDTO("I'm also studying Java", format.parse("13/01/2024"), new AuthorDTO(horika));
        CommentDTO secondPostComment1 = new CommentDTO("That's nice, let's be friends!", format.parse("13/01/2024"), new AuthorDTO(leonardo));

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