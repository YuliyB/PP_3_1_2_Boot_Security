package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initializedDataBase() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        Set<Role> userRoles = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();
        userRoles.add(roleService.saveRole(userRole));
        allRoles.add(roleService.saveRole(adminRole));
        allRoles.add(userRole);
        userService.saveUser(new User("googa", "100", userRoles));
        userService.saveUser(new User("admin", "admin", allRoles));
    }
}
