package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommonRestController {

    private final UsersService usersService;
    private final RolesService rolesService;

    public CommonRestController(UsersService usersService, RolesService rolesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getLoggedUser(Principal principal) {
        User user = usersService.findByEmail(principal.getName()).orElseThrow();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
          List<User> users = usersService.findAll();
          return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = rolesService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return usersService.saveUser(user);
    }

    @PatchMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public User editUser(@RequestBody User user) {
        User newUser = usersService.updateUser(user);
        System.err.println(newUser);
        return newUser;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable int id) {
        usersService.deleteUser(usersService.findById(id));
    }
}
