package com.peaksoft.SpringBootCRUD.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @Column(name = "name_role", nullable = false, unique = true)
    String nameRole;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    @Override
    public String toString() {
        return nameRole;
    }

    @Override
    public String getAuthority() {
        return nameRole;
    }

    public Long getId() {
        return id;
    }

    public String getNameRole() {
        return nameRole;
    }


}
