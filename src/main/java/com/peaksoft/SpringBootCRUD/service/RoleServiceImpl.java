package com.peaksoft.SpringBootCRUD.service;

import com.peaksoft.SpringBootCRUD.entity.Role;
import com.peaksoft.SpringBootCRUD.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public List<String> getNamesOfRolesToList() {
        List<Role> roles = roleRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(role.getRole());
        }
        return list;
    }

    @Override
    public Role getRoleByName(String role) {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().filter(x -> x.getRole().equals(role)).findAny().orElse(new Role());
    }


}
