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
    public String getAsText() {
        System.err.println("get as text called!");
        Role role = (Role) getValue();
        if (role == null) {
            return "";
        }
        return role.getAuthority();
    }

    @Override
    public void setAsText(String text) {
        System.err.println("set as text called!");
        if (text.isEmpty()) {
            setValue(null);
            return;
        }
        System.err.println("Value is not empty!");
        System.err.println("value is: " + text);
        Role role = rolesService.findRoleByName(text);
        System.err.println("role fetched!");
        System.err.println(role.getAuthority());
        setValue(role);
    }
}
