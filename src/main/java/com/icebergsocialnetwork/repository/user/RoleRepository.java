package com.icebergsocialnetwork.repository.user;

import com.icebergsocialnetwork.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
    Role findByName(String name);
}
