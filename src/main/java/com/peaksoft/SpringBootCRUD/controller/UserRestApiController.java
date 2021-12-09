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
@RequestMapping("/")
public class UserRestApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public UserRestApiController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> findAll(){
        try{
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api")
    public ResponseEntity<User> addUser(@RequestBody User user){
        try{
            return new ResponseEntity<>( userService.saveUser(user),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id")int id){
        userService.deleteById(id);
        return "delete done";
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable("id")int id) {
        try{
            return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit")
    public String update(@RequestBody User user){
        userService.saveUser(user);
        return "Update";
    }

    @GetMapping("/user")
    public User getUserMethod(Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}

