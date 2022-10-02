package com.mrkenobii.examserver;

import com.mrkenobii.examserver.model.Role;
import com.mrkenobii.examserver.model.User;
import com.mrkenobii.examserver.model.UserRole;
import com.mrkenobii.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ExamserverApplication(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public static void main(String[] args) {
        SpringApplication.run(ExamserverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Start!!");
//        User user = new User();
//        user.setFirstName("Anil");
//        user.setLastName("Duyguc");
//        user.setUsername("anilduyguc");
//        user.setPassword(this.bCryptPasswordEncoder.encode( "password"));
//        user.setEmail("anilduyguc@abc.com");
//        user.setProfile("default.png");
//
//        Role role = new Role();
//        role.setRoleId(44L);
//        role.setRoleName("ADMIN");
//
//        Set<UserRole> userRoles = new HashSet<>();
//        UserRole userRole = new UserRole();
//        userRole.setRole(role);
//        userRole.setUser(user);
//        userRoles.add(userRole);
//
//        User user1 = this.userService.createUser(user, userRoles);
//        System.out.println(user1.getUsername());

    }
}
