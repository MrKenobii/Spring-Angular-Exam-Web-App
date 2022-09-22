package com.mrkenobii.examserver.service;

import com.mrkenobii.examserver.model.User;
import com.mrkenobii.examserver.model.UserRole;
import com.mrkenobii.examserver.repository.RoleRepository;
import com.mrkenobii.examserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user, Set<UserRole> userRoles){
       /* User userLocal = userRepository.findByUsername(user.getUsername());
        System.out.println("User:" + userLocal);
        if(user != null) {
            throw new RuntimeException("User already exists with username:" + user.getUsername());
        }


        for(UserRole userRole: userRoles){
            roleRepository.save(userRole.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        return userRepository.save(user);*/
        for(UserRole userRole: userRoles){
            roleRepository.save(userRole.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "User deleted with id: " + userId;
    }
}
