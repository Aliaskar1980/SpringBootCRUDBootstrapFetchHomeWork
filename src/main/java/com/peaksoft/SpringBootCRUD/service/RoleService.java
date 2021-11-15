package com.peaksoft.SpringBootCRUD.service;

import com.peaksoft.SpringBootCRUD.entity.Role;

import java.util.List;

public interface    RoleService {
//  getAllRoles
    List<Role> findAll();

    List<String> getNamesOfRolesToList();

    Role getRoleByName(String role);


}
