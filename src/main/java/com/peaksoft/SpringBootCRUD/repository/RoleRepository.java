package com.peaksoft.SpringBootCRUD.repository;


import com.peaksoft.SpringBootCRUD.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNameRole(String role);
}
