package com.example.mongodb_springboot.resources;

import com.example.mongodb_springboot.domain.User;
import com.example.mongodb_springboot.dto.UserDTO;
import com.example.mongodb_springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = service.findAll();
        List<UserDTO> usersDTO = users.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(usersDTO);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<UserDTO> findAll(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping()
    ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
        User user = service.insert(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
