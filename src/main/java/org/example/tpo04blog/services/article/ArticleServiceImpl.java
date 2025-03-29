package org.example.tpo04blog.services.article;

import org.example.tpo04blog.entities.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public List<Article> findAll() {
        return List.of();
    }

    @Override
    public Article findById(Long aLong) {
        return null;
    }

    @Override
    public Article save(Article entity) {
        return null;
    }

    @Override
    public Article update(Long aLong, Article entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
