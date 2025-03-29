package org.example.tpo04blog.controllers.implementations;

import org.example.tpo04blog.controllers.base.AbstractGenericCrudController;
import org.example.tpo04blog.entities.User;
import org.example.tpo04blog.services.GenericCrudService;
import org.example.tpo04blog.services.user.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController extends AbstractGenericCrudController<User, Long> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected GenericCrudService<User, Long> getService() {
        return userService;
    }
}
