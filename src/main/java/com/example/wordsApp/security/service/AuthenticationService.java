package com.example.wordsApp.security.service;

import com.example.wordsApp.model.User;
import com.example.wordsApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthenticationService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        if (user != null){
//            System.out.println(user.get().getUsername());
//            System.out.println(user.get().getId());
//            System.out.println(user.get().getAuthorities());
//            System.out.println(user.get().getPassword());
            return user.get();
        }
        throw new UsernameNotFoundException("User: " + username + " not found");

    }
}
