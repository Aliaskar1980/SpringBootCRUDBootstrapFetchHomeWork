package com.peaksoft.SpringBootCRUD.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_role",nullable = false, unique = true)
    String nameRole;



    @Override
    public String toString() {
        return nameRole;
    }

    @Override
    public String getAuthority() {
        return nameRole;
    }

    public int getId() {
        return id;
    }

    public String getNameRole() {
        return nameRole;
    }
}
