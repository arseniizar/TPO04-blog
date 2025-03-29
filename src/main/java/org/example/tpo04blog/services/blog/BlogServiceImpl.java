package org.example.tpo04blog.services.blog;

import org.example.tpo04blog.entities.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Override
    public List<Blog> findAll() {
        return List.of();
    }

    @Override
    public Blog findById(Long aLong) {
        return null;
    }

    @Override
    public Blog save(Blog entity) {
        return null;
    }

    @Override
    public Blog update(Long aLong, Blog entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
