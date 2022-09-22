package com.mrkenobii.examserver.repository;

import com.mrkenobii.examserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
