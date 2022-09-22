package com.mrkenobii.examserver;

import com.mrkenobii.examserver.model.Role;
import com.mrkenobii.examserver.model.User;
import com.mrkenobii.examserver.model.UserRole;
import com.mrkenobii.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public ExamserverApplication(UserService userService) {
        this.userService = userService;
    }


    public static void main(String[] args) {
        SpringApplication.run(ExamserverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*System.out.println("Start!!");
        User user = new User();
        user.setFirstName("Obi Wan");
        user.setLastName("Kenobi");
        user.setUsername("obiwan-kenobi");
        user.setPassword("abc");
        user.setEmail("obiwan@abc.com");
        user.setProfile("default.png");

        Role role = new Role();
        role.setRoleId(44L);
        role.setRoleName("ADMIN");

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoles.add(userRole);

        User user1 = this.userService.createUser(user, userRoles);
        System.out.println(user1.getUsername());*/

    }
}
