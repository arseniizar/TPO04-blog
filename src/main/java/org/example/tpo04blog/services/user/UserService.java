package org.example.tpo04blog.services.user;

import org.example.tpo04blog.entities.User;
import org.example.tpo04blog.services.base.GenericCrudService;

import java.util.List;

public interface UserService extends GenericCrudService<User, Long> {
    List<User> findUsersByEmailContaining(String keyword);
}
