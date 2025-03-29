package org.example.tpo04blog.services.user;

import org.example.tpo04blog.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User findById(Long aLong) {
        return null;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User update(Long aLong, User entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
