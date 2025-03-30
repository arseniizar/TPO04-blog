package org.example.tpo04blog.controllers.base;

import org.example.tpo04blog.entities.Article;

public interface GenericCrudController<T, ID> {
    void viewAll();

    void add(T entity);

    T searchById(ID id);

    void delete(ID id);
}
