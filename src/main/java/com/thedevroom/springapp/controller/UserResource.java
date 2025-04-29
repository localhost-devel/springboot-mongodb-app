package com.thedevroom.springapp.controller;

import com.thedevroom.springapp.model.User;
import com.thedevroom.springapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    private final UserRepository userRepository;

    @Autowired
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(produces = "application/json")
    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        logger.info("Retrieved {} users", users.size());
        return users;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<User> getUserById(@PathVariable("id") String id) {
        logger.info("Fetching user with id: {}", id);
        return userRepository.findById(id);
    }
}
