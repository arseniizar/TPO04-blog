package org.example.tpo04blog.services.blog;

import org.example.tpo04blog.entities.Blog;
import org.example.tpo04blog.services.base.GenericCrudService;

import java.util.Set;

public interface BlogService extends GenericCrudService<Blog, Long> {
    Blog setBlogManager(Long blogId, Long managerId);

    Blog setBlogArticles(Long blogId, Set<Long> articleIds);
}
