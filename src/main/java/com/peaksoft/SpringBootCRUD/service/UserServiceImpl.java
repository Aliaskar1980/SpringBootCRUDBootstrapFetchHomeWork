package com.peaksoft.SpringBootCRUD.service;

import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private  final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public void mergeUser(User user) {
        userRepository.save(user);

    }

    @Override
    public User getUserByName(String name) {
        List<User> users = findAll();

        return users.stream().filter(x->x.getUsername().equals(name)).findAny().orElse(new User());
    }
}
