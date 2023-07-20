package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommonRestController {

    private final UsersService usersService;

    public CommonRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getLoggedUser(Principal principal) {

        User user = usersService.findUserByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
          List<User> users = usersService.findAll();
          return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@ModelAttribute User user) {
        usersService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED); //todo add catch
    }

    @PatchMapping("/edit")
    public ResponseEntity<User> editUser(@ModelAttribute User user) {
        usersService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK); //todo add catch
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestParam int id) {
        usersService.deleteUser(usersService.findById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
