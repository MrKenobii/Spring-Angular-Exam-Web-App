package com.mrkenobii.examserver.service;

import com.mrkenobii.examserver.helper.UserFoundException;
import com.mrkenobii.examserver.helper.UserNotFoundException;
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

    public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException {
        User userLocal = userRepository.findByUsername(user.getUsername());
        System.out.println("User:" + userLocal);
        if(userLocal != null) {
            throw new UserFoundException("User already exists with username:" + user.getUsername());
        }

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
