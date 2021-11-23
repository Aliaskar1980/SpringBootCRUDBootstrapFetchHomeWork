package com.peaksoft.SpringBootCRUD.service;


import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByUsername(String userName) {
        User user = userRepository.findUserByUsername(userName);
        if (userName==null){
            try {
                throw new NotFoundException(userName);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (email==null){
            try {
                throw new NotFoundException(email);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
