package org.example.tpo04blog.repositories;

import org.example.tpo04blog.entities.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Long> {

}
