package com.example.wordsApp.service;

import com.example.wordsApp.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createNewUser(User user);
    User updateUser(Long id, User user);
    String deleteUser(Long id);
    User getUserById(Long id);
    User getUserByUsername(String username) throws Exception;

}
