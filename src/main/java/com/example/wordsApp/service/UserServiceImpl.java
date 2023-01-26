package com.example.wordsApp.service;

import com.example.wordsApp.exception.UserDeleteException;
import com.example.wordsApp.model.User;
import com.example.wordsApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userToUpdate = getUserById(id);

        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());

        return userRepository.save(userToUpdate);
    }

    @Override
    public String deleteUser(Long id) throws UserDeleteException {
        User user = getUserById(id);

        try {
            userRepository.delete(user);
            //userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserDeleteException(user.getUsername());
        }
        return "User " + id + " deleted";

    }

    @Override
    public User getUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow();

        return user;
    }

//    @Override
//    public User getUserByUsername(String username) throws Exception {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user != null){
//            return user.get();
//        }
//        throw new Exception("Uzytkownik: " + username + " nie zostal znaleziony.");
//    }
}
