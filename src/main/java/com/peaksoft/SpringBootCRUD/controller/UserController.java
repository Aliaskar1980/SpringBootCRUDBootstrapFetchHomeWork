package com.peaksoft.SpringBootCRUD.controller;


import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.service.RoleService;
import com.peaksoft.SpringBootCRUD.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String redirect() {
        return "redirect:/login";
    }

    @GetMapping(value = "login")
    public String login() {
        return "loginPage";
    }

    @GetMapping("/admin")
    public String listUser(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("listUser", userService.getAllUsers());
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "adminPage";
    }

    @GetMapping(value = "user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
//        model.addAttribute("roles", user.getRoles());
        return "userPage";
    }


}
