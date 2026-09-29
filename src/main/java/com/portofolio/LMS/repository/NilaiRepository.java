package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.Nilai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NilaiRepository extends JpaRepository<Nilai, Long>  {

    List<Nilai> findByEnrollmentId(Long enrollmentId);
    List<Nilai> findByEnrollmentIdAndMataPelajaran(Long enrollmentId, String mataPelajaran);
}
