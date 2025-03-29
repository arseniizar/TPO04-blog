package org.example.tpo04blog.services.role;

import org.example.tpo04blog.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findAll() {
        return List.of();
    }

    @Override
    public Role findById(Long aLong) {
        return null;
    }

    @Override
    public Role save(Role entity) {
        return null;
    }

    @Override
    public Role update(Long aLong, Role entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
