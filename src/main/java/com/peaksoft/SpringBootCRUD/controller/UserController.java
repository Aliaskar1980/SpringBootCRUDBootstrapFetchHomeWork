package com.peaksoft.SpringBootCRUD.controller;



import com.peaksoft.SpringBootCRUD.entity.Role;
import com.peaksoft.SpringBootCRUD.entity.User;
import com.peaksoft.SpringBootCRUD.service.RoleService;
import com.peaksoft.SpringBootCRUD.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "main-page";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {

        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String showUserList1(Model model) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

//    @GetMapping("/users")
//    //localhost:1000/users
//    public String showUserList(Model model) {
//        List<User> listUsers = userService.getAllUser();
//        model.addAttribute("listUsers", listUsers);
//        return "users";
//    }

//    @PostMapping("/user")
//
//    }

    @GetMapping("/users/new")
    public String showNewForm(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute("user", user);
        model.addAttribute("user", new User());
        List<Role> roles = roleService.findAll();
        model.addAttribute("allRoles", roles);
        System.out.println(model.addAttribute("allRoles", roles));
        return "user_form";
    }

    @PostMapping("/users/save")
    public String createUser(User user, @RequestParam Map<String, String> form) {
        List<String> roles = roleService.getNamesOfRolesToList();

        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
                System.out.println(roles + " " + roleService.getRoleByName(key));
            }
        }
        userService.mergeUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.findById(id);
        model.addAttribute("user", user);

        List<Role> roles = roleService.findAll();
        model.addAttribute("allRoles", roles);

        return "user_form";
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(User user, @RequestParam Map<String, String> form) {
        List<String> roles = roleService.getNamesOfRolesToList();

        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
                System.out.println(roles + " " + roleService.getRoleByName(key));
            }
        }

        userService.mergeUser(user);
        return "redirect:/admin";
    }
//    public String updateUser(User user) {
//        userService.mergeUser(user);
//        return "redirect:/admin";
//    }


    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
