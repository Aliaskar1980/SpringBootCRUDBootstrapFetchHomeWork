package com.peaksoft.SpringBootCRUD.service;


import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

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
}