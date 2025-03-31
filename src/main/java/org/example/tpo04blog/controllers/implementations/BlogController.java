package org.example.tpo04blog.controllers.implementations;

import org.example.tpo04blog.controllers.base.AbstractGenericCrudController;
import org.example.tpo04blog.entities.Blog;
import org.example.tpo04blog.services.base.GenericCrudService;
import org.example.tpo04blog.services.blog.BlogService;
import org.springframework.stereotype.Controller;

import java.util.Set;

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

    public void setManager(Long blogId, Long managerId) {
        blogService.setBlogManager(blogId, managerId);
    }

    public void setArticles(Long blogId, Set<Long> articleIds) {
        blogService.setBlogArticles(blogId, articleIds);
    }
}
