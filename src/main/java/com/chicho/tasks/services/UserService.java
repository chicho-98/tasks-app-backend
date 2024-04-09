package com.chicho.tasks.services;

import com.chicho.tasks.model.user.User;
import com.chicho.tasks.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDetails getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public UserDetails createUser(User newUser) {
        return userRepository.save(newUser);
    }
}
