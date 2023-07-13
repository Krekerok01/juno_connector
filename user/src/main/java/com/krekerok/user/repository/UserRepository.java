package com.krekerok.user.repository;

import com.krekerok.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);
}
