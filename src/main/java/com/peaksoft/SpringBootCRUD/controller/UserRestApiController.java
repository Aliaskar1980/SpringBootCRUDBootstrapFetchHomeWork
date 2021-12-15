package com.peaksoft.SpringBootCRUD.controller;

import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.service.RoleService;
import com.peaksoft.SpringBootCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public UserRestApiController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
//получить всех пользователей
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> findAll() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//добавить нового пользователя
    @PostMapping(value = "")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//удалить пользователя по id
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "delete done";
    }
//найти пользователя по ID
    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        userService.findById(id);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/user")
    public User getUserMethod(Principal principal) {
        return userService.findByUsername(principal.getName());
    }



//    @PutMapping("/edit")
//    public String update(@RequestBody User user) {
//        userService.saveUser(user);
//        return "Update";
//    }

}

