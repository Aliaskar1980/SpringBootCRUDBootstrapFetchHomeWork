package com.peaksoft.SpringBootCRUD.service;


import com.peaksoft.SpringBootCRUD.config.handler.PasswordConfig;
import com.peaksoft.SpringBootCRUD.entity.Role;
import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.repository.RoleRepository;
import com.peaksoft.SpringBootCRUD.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
//@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;//14/12
    private final PasswordConfig passwordConfig;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordConfig passwordConfig) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordConfig = passwordConfig;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @Override
//    public User register(User user) {
//        Role roleUser = (Role) roleRepository.findByNameRole("ROLE_USER");
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(userRoles);
//
//        return null;
//    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById((long) id).get();
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById((long) id);
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }


//    public void updateUser(Long id, User user) {
//        Set<Role> roleSet = new HashSet<>();
//        if (user.getRoles() != null) {
//            for (Role role : user.getRoles()) {
//                roleSet.add(roleRepository.findByNameRole(role.getNameRole()));
//            }
//        } else {
//            for (Role existingRole : userRepository.getById(id).getRoles()) {
//                roleSet.add(existingRole);
//            }
//        }
//        user.setRoles(roleSet);
//
//        String oldPassword = user.getPassword();
//        try {
//            if (user.getPassword().equals(oldPassword)) {
//                user.setPassword(oldPassword);
//            } else {
//                passwordConfig.getPasswordEncoder().encode(user.getPassword());
//            }
//            userRepository.save(user);
//        } catch (DataIntegrityViolationException e) {
//            user.setPassword(oldPassword);
//        }
//    }

}