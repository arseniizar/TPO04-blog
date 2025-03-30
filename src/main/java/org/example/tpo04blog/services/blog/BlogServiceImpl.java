package org.example.tpo04blog.services.blog;

import org.example.tpo04blog.entities.Blog;
import org.example.tpo04blog.repositories.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> findAll() {
        List<Blog> result = new ArrayList<>();
        blogRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Blog findById(Long id) {
        if (id == null) throw new IllegalArgumentException("Blog ID cannot be null");
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));
    }

    @Override
    public Blog save(Blog entity) {
        if (entity == null) throw new IllegalArgumentException("Blog entity cannot be null");
        return blogRepository.save(entity);
    }

    @Override
    public Blog update(Long id, Blog entity) {
        if (id == null || entity == null) throw new IllegalArgumentException("Blog ID and entity cannot be null");
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
}
