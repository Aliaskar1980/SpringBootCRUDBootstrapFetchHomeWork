package com.peaksoft.SpringBootCRUD.service;



import com.peaksoft.SpringBootCRUD.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void add(Role role);

    void edit(Role role);

    Role getById(int id);

    Role getRoleByName(String name);
    List<String> getNamesOfRoles();
}
