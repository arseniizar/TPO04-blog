package org.example.tpo04blog.controllers.implementations;

import org.example.tpo04blog.controllers.base.AbstractGenericCrudController;
import org.example.tpo04blog.entities.Blog;
import org.example.tpo04blog.services.base.GenericCrudService;
import org.example.tpo04blog.services.blog.BlogService;
import org.springframework.stereotype.Controller;

@Controller
public class BlogController extends AbstractGenericCrudController<Blog, Long> {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Override
    protected GenericCrudService<Blog, Long> getService() {
        return blogService;
    }
}
