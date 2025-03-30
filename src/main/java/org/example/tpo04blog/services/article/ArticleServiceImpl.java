package org.example.tpo04blog.services.article;

import org.example.tpo04blog.entities.Article;
import org.example.tpo04blog.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll() {
        List<Article> result = new ArrayList<>();
        articleRepository.findAll().forEach(result::add);
        return result;
    }


    @Override
    public Article findById(Long id) {
        if (id == null) throw new IllegalArgumentException("Article ID cannot be null");
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
    }

    @Override
    public Article save(Article entity) {
        if (entity == null) throw new IllegalArgumentException("Article entity cannot be null");
        return articleRepository.save(entity);
    }

    @Override
    public Article update(Long id, Article entity) {
        if (id == null || entity == null) throw new IllegalArgumentException("Article ID and entity cannot be null");
        Article existing = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        existing.setTitle(entity.getTitle());
        existing.setAuthor(entity.getAuthor());
        existing.setBlog(entity.getBlog());
        return articleRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Article ID cannot be null");
        if (!articleRepository.existsById(id)) throw new RuntimeException("Article not found with id: " + id);
        articleRepository.deleteById(id);
    }
}
