package org.example.tpo04blog.services.article;

import org.example.tpo04blog.entities.Article;
import org.example.tpo04blog.entities.Blog;
import org.example.tpo04blog.entities.User;
import org.example.tpo04blog.repositories.ArticleRepository;
import org.example.tpo04blog.repositories.BlogRepository;
import org.example.tpo04blog.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository, BlogRepository blogRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
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

    @Override
    @Transactional
    public Article createArticleWithAssociations(String title, Long authorId, Long blogId) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Article title cannot be empty");
        if (authorId == null) throw new IllegalArgumentException("Author ID cannot be null");
        if (blogId == null) throw new IllegalArgumentException("Blog ID cannot be null");

        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));

        Article newArticle = new Article();
        newArticle.setTitle(title);
        newArticle.setAuthor(author);
        newArticle.setBlog(blog);

        return articleRepository.save(newArticle);
    }

    @Override
    @Transactional
    public Article assignArticleAuthor(Long articleId, Long authorId) {
        if (articleId == null) throw new IllegalArgumentException("Article ID cannot be null");
        if (authorId == null) throw new IllegalArgumentException("New Author ID cannot be null");

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + articleId));

        User newAuthor = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("New author (User) not found with id: " + authorId));

        User oldAuthor = article.getAuthor();
        if (oldAuthor != null && oldAuthor.getArticles() != null) {
            oldAuthor.getArticles().remove(article);
            userRepository.save(oldAuthor);
        }

        article.setAuthor(newAuthor);

        if (newAuthor.getArticles() != null) {
            newAuthor.getArticles().add(article);
        }

        return articleRepository.save(article);
    }
}
