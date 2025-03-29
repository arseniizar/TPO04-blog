package org.example.tpo04blog.repositories;

import org.example.tpo04blog.entities.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
}
