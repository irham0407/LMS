package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.Kelas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KelasRepository extends JpaRepository<Kelas, Long> {

    boolean existsByNamaKelas(String namaKelas);
}
