package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_REDIRECT = "redirect:/admin/users";

    UsersService usersService;

    public AdminController(UsersService usersService) {
        this.usersService = usersService;
    }



    @GetMapping("/users")
    public String admin() {
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        usersService.saveUser(user);
        return ADMIN_REDIRECT;
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        usersService.updateUser(user);
        return ADMIN_REDIRECT;
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute User user) {
        usersService.deleteUser(user);
        return ADMIN_REDIRECT;
    }

    @ModelAttribute("allUsers")
    public List<User> allUsers() {
        return usersService.findAll();
    }
}
