package org.example.tpo04blog.services.base;

import java.util.List;

public interface GenericCrudService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T save(T entity);

    T update(ID id, T entity);

    void delete(ID id);
}
