package com.chefatands.ingredient.service;

import com.chefathands.ingredient.model.User;
import com.chefathands.ingredient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public int register(String username, String passwordHash, String email) {
        if(userRepository.findByUsername(username).isPresent()) {
            return -1;
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setEmail(email);
        User.saved = userRepository.save(user);
        return saved.getId();
    }

    public int exists(String username, String passwordHash) {
        return userRepository.findByUsernameAndPassword(Username, passwordHash)
                .map(User::getId)
                .orElse(-1);
    }
}