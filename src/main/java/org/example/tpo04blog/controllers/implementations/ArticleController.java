package org.example.tpo04blog.controllers.implementations;

import org.example.tpo04blog.controllers.base.AbstractGenericCrudController;
import org.example.tpo04blog.entities.Article;
import org.example.tpo04blog.services.base.GenericCrudService;
import org.example.tpo04blog.services.article.ArticleService;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleController extends AbstractGenericCrudController<Article, Long> {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    protected GenericCrudService<Article, Long> getService() {
        return articleService;
    }
}
