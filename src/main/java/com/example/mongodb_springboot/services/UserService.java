package com.example.mongodb_springboot.services;

import com.example.mongodb_springboot.domain.User;
import com.example.mongodb_springboot.dto.UserDTO;
import com.example.mongodb_springboot.exceptions.ObjectNotFoundException;
import com.example.mongodb_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id " + id + "."));
    }

    public User insert(UserDTO obj) {
        User user = fromDTO(obj);
        return repository.insert(user);
    }

    private User fromDTO(UserDTO obj) {
        return new User(obj.getId(), obj.getName(), obj.getName());
    }
}