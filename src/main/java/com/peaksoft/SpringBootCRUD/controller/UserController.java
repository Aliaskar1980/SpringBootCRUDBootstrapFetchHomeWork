package com.peaksoft.SpringBootCRUD.controller;


import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.service.RoleService;
import com.peaksoft.SpringBootCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("admin")
    public String listUser(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("listUser", userService.getAllUsers());
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "adminPage";

//----------------------
//    @GetMapping("/admin")
//    public String admin() {
//        return "adminPage";
//    }

//    @GetMapping("/")
//    public String login() {
//        return "redirect:login";
//    }
//-----------------------------------
//    @GetMapping("/user")
//    public String getUser(Principal principal, Model model) {
//        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        return "userPage";
//    }

    }
}
