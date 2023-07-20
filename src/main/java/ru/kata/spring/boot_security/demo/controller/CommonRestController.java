package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommonRestController {

    private final UsersService usersService;

    public CommonRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(@ModelAttribute int id) {
//        User user = usersService.findById(id);
        return new ResponseEntity<>("user", HttpStatus.OK); //todo add catch exception
    }

    @GetMapping("/users")
    public ResponseEntity<?> users() {
          List<User> users = usersService.findAll();
          return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> add(@ModelAttribute User user) {
        usersService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED); //todo add catch
    }

    @PatchMapping("/edit")
    public ResponseEntity<User> edit(@ModelAttribute User user) {
        usersService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK); //todo add catch
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> delete(@ModelAttribute User user) {
        usersService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK); //todo add catch
    }
}
