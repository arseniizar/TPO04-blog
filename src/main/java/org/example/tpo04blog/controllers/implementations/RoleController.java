package org.example.tpo04blog.controllers.implementations;

import org.example.tpo04blog.controllers.base.AbstractGenericCrudController;
import org.example.tpo04blog.entities.Role;
import org.example.tpo04blog.services.base.GenericCrudService;
import org.example.tpo04blog.services.role.RoleService;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController extends AbstractGenericCrudController<Role, Long> {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    protected GenericCrudService<Role, Long> getService() {
        return roleService;
    }
}
