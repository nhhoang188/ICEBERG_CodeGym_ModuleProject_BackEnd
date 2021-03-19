package com.icebergsocialnetwork.repository.user;

import com.icebergsocialnetwork.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findUserById(Long id);

    User findUserByIdAndInfomodifierIsTrue(Long id);
}
