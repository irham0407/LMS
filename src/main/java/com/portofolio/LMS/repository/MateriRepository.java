package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.Materi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriRepository extends JpaRepository<Materi, Long> {

    List<Materi> findByMataPelajaran(String mataPelajaran);
}
