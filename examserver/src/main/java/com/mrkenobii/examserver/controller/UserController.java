package com.mrkenobii.examserver.controller;

import com.mrkenobii.examserver.model.Role;
import com.mrkenobii.examserver.model.User;
import com.mrkenobii.examserver.model.UserRole;
import com.mrkenobii.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        user.setProfile("default.png");
        Set<UserRole> userRoleSet = new HashSet<>();
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleSet.add(userRole);

        return userService.createUser(user, userRoleSet);
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId){
        return userService.deleteUser(userId);
    }
}
