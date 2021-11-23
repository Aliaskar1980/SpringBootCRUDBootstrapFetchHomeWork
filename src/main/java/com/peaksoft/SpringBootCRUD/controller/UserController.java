package com.peaksoft.SpringBootCRUD.controller;


import com.peaksoft.SpringBootCRUD.entity.Role;
import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.service.RoleService;
import com.peaksoft.SpringBootCRUD.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping("/")
//    public String getHomePage() {
//        return "home-page";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("admin")
    public String listUser(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("listUser", userService.getAllUsers());
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("user",user);
        return "adminPage";
    }

    @GetMapping("user")
    public String infoUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "userPage";
    }
//
//    @GetMapping(value = "admin/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "createNew";
//    }

    @PostMapping(value = "admin/new")
    public String newUser(@ModelAttribute User user,
                          @RequestParam(value = "rolez") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(roleService.getByName(roles));
        }
        user.setRoles(roleSet);
        userService.save(user);
        return "redirect:/admin";
    }

//    @GetMapping(value = "admin/edit/{id}")
//    public String editUser(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getById(id));
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "editUser";
//    }

    @PostMapping(value = "admin/edit/{id}")
    public String editUser(@ModelAttribute User user, @RequestParam(value = "rolez") String[] role) {

        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(roleService.getByName(roles));
        }
        user.setRoles(roleSet);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}
