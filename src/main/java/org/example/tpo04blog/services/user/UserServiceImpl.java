package org.example.tpo04blog.services.user;

import org.example.tpo04blog.entities.User;
import org.example.tpo04blog.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public User findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User save(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("User entity cannot be null");
        }
        return userRepository.save(entity);
    }

    @Override
    public User update(Long id, User entity) {
        if (id == null || entity == null) {
            throw new IllegalArgumentException("User ID and entity cannot be null");
        }
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        existingUser.setEmail(entity.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findUsersByEmailContaining(String keyword) {
        return userRepository.findUsersByEmailContaining(keyword);
    }
}
