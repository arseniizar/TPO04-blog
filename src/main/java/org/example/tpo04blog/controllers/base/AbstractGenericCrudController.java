package org.example.tpo04blog.controllers.base;

import org.example.tpo04blog.controllers.base.GenericCrudController;
import org.example.tpo04blog.services.GenericCrudService;

import java.util.List;

public abstract class AbstractGenericCrudController<T, ID> implements GenericCrudController<T, ID> {

    protected abstract GenericCrudService<T, ID> getService();

    @Override
    public void viewAll() {
        List<T> entities = getService().findAll();
        System.out.println("All records:");
        entities.forEach(System.out::println);
    }

    @Override
    public void add(T entity) {
        T saved = getService().save(entity);
        System.out.println("Added: " + saved);
    }

    @Override
    public void searchById(ID id) {
        T entity = getService().findById(id);
        System.out.println("Found: " + entity);
    }

    @Override
    public void delete(ID id) {
        getService().delete(id);
        System.out.println("Deleted entity with ID: " + id);
    }
}
