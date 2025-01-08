package com.example.mongodb_springboot.config;

import com.example.mongodb_springboot.domain.User;
import com.example.mongodb_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User leonardo = new User(null, "Leonardo", "leonardo@gmail.com");
        User aynokoji = new User(null, "Aynokoji", "aynokoji@gmail.com");
        User horika = new User(null, "Horika", "horika@gmail.com");

        userRepository.saveAll(Arrays.asList(leonardo, aynokoji, horika));
    }
}