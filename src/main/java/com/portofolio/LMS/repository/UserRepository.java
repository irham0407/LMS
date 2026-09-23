package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByNikNis(String nikNis);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByNikNis(String nikNis);
}
