package org.example.tpo04blog.services.blog;

import org.example.tpo04blog.entities.Article;
import org.example.tpo04blog.entities.Blog;
import org.example.tpo04blog.entities.User;
import org.example.tpo04blog.repositories.ArticleRepository;
import org.example.tpo04blog.repositories.BlogRepository;
import org.example.tpo04blog.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public BlogServiceImpl(BlogRepository blogRepository,
                           UserRepository userRepository,
                           ArticleRepository articleRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Blog> findAll() {
        List<Blog> result = new ArrayList<>();
        blogRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Blog findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Blog ID cannot be null");
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));
    }

    @Override
    public Blog save(Blog entity) {
        if (entity == null)
            throw new IllegalArgumentException("Blog entity cannot be null");
        return blogRepository.save(entity);
    }

    @Override
    public Blog update(Long id, Blog entity) {
        if (id == null || entity == null)
            throw new IllegalArgumentException("Blog ID and entity cannot be null");
        Blog existing = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));
        existing.setName(entity.getName());
        return blogRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Blog ID cannot be null");
        if (!blogRepository.existsById(id)) throw new RuntimeException("Blog not found with id: " + id);
        blogRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Blog setBlogManager(Long blogId, Long managerId) {
        if (blogId == null) throw new IllegalArgumentException("Blog ID cannot be null");
        if (managerId == null) throw new IllegalArgumentException("Manager (User) ID cannot be null");

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() ->
                        new RuntimeException("Blog not found with id: " + blogId));
        User manager = userRepository.findById(managerId)
                .orElseThrow(() ->
                        new RuntimeException("Manager (User) not found with id: " + managerId));

        blog.setManager(manager);
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public Blog setBlogArticles(Long blogId, Set<Long> articleIds) {
        if (blogId == null) throw new IllegalArgumentException("Blog ID cannot be null");
        if (articleIds == null) throw new IllegalArgumentException("Article IDs set cannot be null");

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));

        Set<Article> newArticles = new HashSet<>();
        Set<Long> notFoundIds = new HashSet<>();

        if (!articleIds.isEmpty()) {
            List<Article> foundArticles = new ArrayList<>();

            articleRepository.findAllById(articleIds).forEach(foundArticles::add);

            Set<Long> foundArticleIds = foundArticles.stream()
                    .map(Article::getId)
                    .collect(Collectors.toSet());

            for (Long articleId : articleIds) {
                if (!foundArticleIds.contains(articleId)) {
                    notFoundIds.add(articleId);
                }
            }
            newArticles.addAll(foundArticles);

            if (!notFoundIds.isEmpty()) {
                throw new RuntimeException("Some articles not found with IDs: " + notFoundIds);
            }
        }

        Set<Article> currentArticles = blog.getArticles();
        if (currentArticles == null) {
            currentArticles = new HashSet<>();
            blog.setArticles(currentArticles);
        }

        Set<Article> articlesToRemove = new HashSet<>(currentArticles);
        articlesToRemove.removeAll(newArticles);

        Set<Article> articlesToAdd = new HashSet<>(newArticles);
        articlesToAdd.removeAll(currentArticles);

        // setting null in order to avoid orphans

        for (Article articleToRemove : articlesToRemove) {
            currentArticles.remove(articleToRemove);
            articleToRemove.setBlog(null);
        }

        for (Article articleToAdd : articlesToAdd) {
            currentArticles.add(articleToAdd);
            articleToAdd.setBlog(blog);
        }

        return blogRepository.save(blog);
    }
}
