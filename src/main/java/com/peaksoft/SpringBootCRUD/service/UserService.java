package com.peaksoft.SpringBootCRUD.service;


import com.peaksoft.SpringBootCRUD.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface UserService {
    void save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteById(Long id);

    void mergeUser(User user);

    User getUserByName(String name);


}
