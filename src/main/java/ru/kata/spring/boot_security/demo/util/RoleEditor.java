package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RolesService;

import java.beans.PropertyEditorSupport;

public class RoleEditor extends PropertyEditorSupport {
    private final RolesService rolesService;

    @Autowired
    public RoleEditor(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    public void setAsText(String text) {
        if (text.isEmpty()) {
            setValue(null);
            return;
        }
        Role role = rolesService.findRoleByName("ROLE_" + text);
        setValue(role);
    }
}
