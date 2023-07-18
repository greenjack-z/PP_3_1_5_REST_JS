package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repository.RolesRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService{

    private final RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Role findRoleByName(String roleName) {
        return rolesRepository.findRoleByAuthority(roleName).orElse(new Role().setAuthority(roleName));
    }

    @Override
    public List<Role> findAll() {
        return rolesRepository.findAll();
    }

}
