package org.example.tpo04blog.services.role;

import org.example.tpo04blog.entities.Role;
import org.example.tpo04blog.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        List<Role> result = new ArrayList<>();
        roleRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Role findById(Long id) {
        if (id == null) throw new IllegalArgumentException("Role ID cannot be null");
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    @Override
    public Role save(Role entity) {
        if (entity == null) throw new IllegalArgumentException("Role entity cannot be null");
        return roleRepository.save(entity);
    }

    @Override
    public Role update(Long id, Role entity) {
        if (id == null || entity == null) throw new IllegalArgumentException("Role ID and entity cannot be null");
        Role existing = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        existing.setName(entity.getName());
        return roleRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Role ID cannot be null");
        if (!roleRepository.existsById(id)) throw new RuntimeException("Role not found with id: " + id);
        roleRepository.deleteById(id);
    }
}
