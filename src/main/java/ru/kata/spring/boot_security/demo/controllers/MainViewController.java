package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class MainViewController {

    RoleService roleService;

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String ShowAdminPage (Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "bootstrap/admin";
    }

    @GetMapping("/user")
    public String showUserPage () {
        return "bootstrap/user";
    }

    @GetMapping()
    public String redirectToExistingPage (Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        return currentUser.getRolesToString().contains("ADMIN") ?
                "redirect:/admin" :
                "redirect:/user";
    }
}
