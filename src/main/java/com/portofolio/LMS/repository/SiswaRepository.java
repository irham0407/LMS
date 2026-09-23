package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiswaRepository extends JpaRepository<Siswa, Long> {

    Optional<Siswa> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
