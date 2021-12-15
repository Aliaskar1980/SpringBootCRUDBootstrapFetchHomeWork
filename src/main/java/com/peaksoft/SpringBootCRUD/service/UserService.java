package com.peaksoft.SpringBootCRUD.service;



import com.peaksoft.SpringBootCRUD.entity.User;

import java.util.List;

public interface UserService {
//    User register (User user);//14/12/
    User saveUser(User user);
    List<User> getAllUsers();
    User findByUsername(String username);
    User findById(int id);
    void deleteById(int id);
//    void updateUser(Long id, User user);


}
