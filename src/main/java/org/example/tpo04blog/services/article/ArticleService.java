package org.example.tpo04blog.services.article;

import org.example.tpo04blog.entities.Article;
import org.example.tpo04blog.services.base.GenericCrudService;

public interface ArticleService extends GenericCrudService<Article, Long> {
    Article createArticleWithAssociations(String title, Long authorId, Long blogId);

    Article assignArticleAuthor(Long articleId, Long authorId);
}
