package com.peaksoft.SpringBootCRUD.service;


import com.peaksoft.SpringBootCRUD.entity.Role;
import com.peaksoft.SpringBootCRUD.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void add(Role role) {
        if (roleRepository.findByNameRole(role.getNameRole())==null){
            roleRepository.save(role);
        }
    }

    @Override
    public void edit(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getById(int id) {
        Role role = null;
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> roles = roleRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Role role : roles) {
            if (role.getNameRole().equals(name)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public List<String> getNamesOfRoles() {
        List<Role> roles = roleRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(role.getNameRole());
        }
        return list;
    }


}
