package ru.kata.spring.boot_security.demo.controller;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;
import ru.kata.spring.boot_security.demo.util.RoleEditor;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_REDIRECT = "redirect:/admin/users";

    private final UsersService usersService;
    private final RolesService rolesService;

    public AdminController(UsersService usersService, RolesService rolesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @GetMapping({"", "/", "/users"})
    public String getUsersPage() {
        return "users";
    }

    @GetMapping("/new")
    public String getAddUserPage() {
        return "new";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {

        System.err.println("we've got a user:");
        System.err.println(user);


        usersService.saveUser(user);
        return ADMIN_REDIRECT;
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute User user) {
        usersService.updateUser(user);
        return ADMIN_REDIRECT;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        usersService.deleteUser(usersService.findById(id));
        return ADMIN_REDIRECT;
    }

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {
        return usersService.findAll();
    }

    @ModelAttribute("roles")
    public List<Role> getAllRoles() {
        return rolesService.findAll();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Role.class, new RoleEditor(rolesService));
    }

}
