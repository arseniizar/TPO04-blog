package org.example.tpo04blog.controllers.base;

public interface GenericCrudController<T, ID> {
    void viewAll();

    void add(T entity);

    void searchById(ID id);

    void delete(ID id);
}
