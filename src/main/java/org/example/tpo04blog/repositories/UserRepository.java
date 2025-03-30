package org.example.tpo04blog.repositories;

import org.example.tpo04blog.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email LIKE %:keyword%")
    List<User> findUsersByEmailContaining(@Param("keyword") String keyword);
}
