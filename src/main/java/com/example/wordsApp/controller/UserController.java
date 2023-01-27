package com.example.wordsApp.controller;

import com.example.wordsApp.model.User;
import com.example.wordsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/all")
    public String AllUsers() {

        return "Dostep dla wszytskich";
    }

    @PostMapping("/users/sign-up")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        User response = userService.createNewUser(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User response = userService.updateUser(id, user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        User response = userService.getUserById(id);
        return response;
    }

//    @GetMapping("/users/{username}")
//        public User getUserByUsername(@PathVariable String username) throws Exception {
//        User response = userService.getUserByUsername(username);
//        return response;
//        }

}
