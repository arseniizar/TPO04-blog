package org.example.tpo04blog.repositories;

import org.example.tpo04blog.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
