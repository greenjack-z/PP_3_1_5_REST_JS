package ru.kata.spring.boot_security.demo.controller;


import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {

    private static final String ADMIN_REDIRECT = "redirect:/admin";

    UsersService usersService;

    public Controller(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute User user) {
        usersService.saveUser(user);
        return ADMIN_REDIRECT;
    }

    @PatchMapping("/admin/update")
    public String updateUser(@ModelAttribute User user) {
        usersService.updateUser(user);
        return ADMIN_REDIRECT;
    }

    @DeleteMapping("/admin/delete")
    public String deleteUser(@ModelAttribute User user) {
        usersService.deleteUser(user);
        return ADMIN_REDIRECT;
    }

    @ModelAttribute("allUsers")
    public List<User> allUsers() {
        return usersService.findAll();
    }

}
