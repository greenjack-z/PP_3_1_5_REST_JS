package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RolesService {
    Role findRoleByName(String roleName);
}
