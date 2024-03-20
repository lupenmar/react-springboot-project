package com.lupenmar.springtodo.controller;

import com.lupenmar.springtodo.model.User;
import com.lupenmar.springtodo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> findAllUser() {

        return service.findAllUser();
    }

    @PostMapping("create_user")
    public String createUser(@RequestBody User user) {
        service.createUser(user);
        return "User successfully created";
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @PutMapping("update_user")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("delete_user/{username}")
    public void deleteUser(@PathVariable String username) {
        service.deleteUser(username);
    }

}
