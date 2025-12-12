package com.chefathands.users.service;

import com.chefathands.users.dto.*;
import com.chefathands.users.exception.InvalidCredentialsException;
import com.chefathands.users.exception.UserNotFoundException;
import com.chefathands.users.exception.UsernameAlreadyExistsException;
import com.chefathands.users.model.User;
import com.chefathands.users.repository.UserRepository;
import com.chefathands.users.util.PasswordHasher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chefathands.logging.service.LogService;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LogService logService;

    public UserService(UserRepository userRepository, LogService logService) {
        this.userRepository = userRepository;
        this.logService = logService;
    }

    /**
     * Register a new user
     */
    @Transactional
    public UserResponse registerUser(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            logService.logWarn("Attempted registration with existing username" + request.getUsername());
            throw new UsernameAlreadyExistsException(request.getUsername());
        }

        // Hash the password
        String hashedPassword = PasswordHasher.hashPassword(request.getPassword());

        // Create and save user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(hashedPassword);
        user.setEmail(request.getEmail());
        
        User saved = userRepository.save(user);
        logService.logInfo("New user registered: " + saved.getUsername() + "(id= " + saved.getId() + ")");
        
        return mapToUserResponse(saved);
    }

    /**
     * Login user (verify credentials)
     */
    public LoginResponse login(LoginRequest request) {
        // Find user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    logService.logError("Login failed: username not found -> " + request.getUsername());
                    return new InvalidCredentialsException();
                });

        // Verify password
        if (!PasswordHasher.verifyPassword(request.getPassword(), user.getPasswordHash())) {
            logService.logError("Login failed: invalid passwordd for user -> " + request.getUsername());
            throw new InvalidCredentialsException();
        }

        logService.logInfo("User logged in successfully: " + user.getUsername());
        return new LoginResponse(
            user.getId(),
            user.getUsername(),
            "Login successful"
        );
    }

    /**
     * Get user by ID
     */
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logService.logError("User not found: " + id);
                    return new UserNotFoundException(id);
                });
        
        logService.logInfo("User found by id");
        return mapToUserResponse(user);
    }

    /**
     * Update user profile
     */
    @Transactional
    public UserResponse updateUser(Integer id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Update username if provided and check uniqueness
        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
            if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                logService.logError("Username already exists: " + request.getUsername());
                throw new UsernameAlreadyExistsException(request.getUsername());
            }
            user.setUsername(request.getUsername());
        }

        // Update email if provided
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        // Update password if provided
        if (request.getPassword() != null) {
            String hashedPassword = PasswordHasher.hashPassword(request.getPassword());
            user.setPasswordHash(hashedPassword);
        }

        User updated = userRepository.save(user);
        logService.logInfo("User updated successfully: " + user.getUsername());
        return mapToUserResponse(updated);
    }

    /**
     * Delete user
     */
    @Transactional
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            logService.logError("User ist found: " + id);
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Check if username exists
     */
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Map User entity to UserResponse DTO
     */
    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
    }

    // Legacy methods (kept for backward compatibility, deprecated)
    @Deprecated
    public int register(String username, String passwordHash, String email) {
        if(userRepository.findByUsername(username).isPresent()) {
            return -1;
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setEmail(email);
        User saved = userRepository.save(user);
        return saved.getId();
    }

    @Deprecated
    public int exists(String username, String passwordHash) {
        return userRepository.findByUsernameAndPasswordHash(username, passwordHash)
                .map(User::getId)
                .orElse(-1);
    }
}