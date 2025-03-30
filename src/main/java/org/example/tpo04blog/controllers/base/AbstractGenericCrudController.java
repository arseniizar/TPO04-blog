package org.example.tpo04blog.controllers.base;

import org.example.tpo04blog.entities.Article;
import org.example.tpo04blog.services.base.GenericCrudService;

import java.util.List;

public abstract class AbstractGenericCrudController<T, ID> implements GenericCrudController<T, ID> {

    protected abstract GenericCrudService<T, ID> getService();

    @Override
    public void viewAll() {
        List<T> entities = getService().findAll();
        entities.forEach(System.out::println);
    }

    @Override
    public void add(T entity) {
        T saved = getService().save(entity);
    }

    @Override
    public T searchById(ID id) {
        return getService().findById(id);
    }

    @Override
    public void delete(ID id) {
        getService().delete(id);
    }

    @Override
    public void update(ID id, T entity) {
        getService().update(id, entity);
    }
}
