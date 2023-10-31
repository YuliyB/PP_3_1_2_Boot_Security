package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public String printUsers(Model model, Principal principal) {
        model.addAttribute("currentuser",userService.findByUsername(principal.getName()));
        model.addAttribute("allusers", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "bootstrap/admin";
    }

    @PostMapping ("/delete")
    public String deleteUser(@RequestParam(value = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user) {
        if (userService.findById(user.getId()) == null) {
            return "doesntExist";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        if (userService.saveUser(user)) {
            return "redirect:/admin";
        } else {
            return "alreadyExists";
        }
    }
}
