package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.Guru;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuruRepository extends JpaRepository<Guru, Long> {

    Optional<Guru> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
