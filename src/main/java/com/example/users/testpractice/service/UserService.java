package com.example.users.testpractice.service;

import com.example.users.testpractice.dto.UserRequest;
import com.example.users.testpractice.dto.UserResponse;
import com.example.users.testpractice.entity.User;
import com.example.users.testpractice.exception.UserNotFoundException;
import com.example.users.testpractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User(request.name(), request.email());
        return toResponse(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.updateDetails(request.name(), request.email());
        return toResponse(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
