package org.example.tpo04blog.controllers.base;

public interface GenericCrudController<T, ID> {
    void viewAll();

    void add(T entity);

    T searchById(ID id);

    void delete(ID id);

    void update(ID id, T entity);
}
