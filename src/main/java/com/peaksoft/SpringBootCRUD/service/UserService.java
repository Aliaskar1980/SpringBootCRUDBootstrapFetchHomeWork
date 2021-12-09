package com.peaksoft.SpringBootCRUD.service;



import com.peaksoft.SpringBootCRUD.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User findById(int id);
    void deleteById(int id);
    User findByUsername(String username);
}
