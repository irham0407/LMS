package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.Absensi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AbsensiRepository extends JpaRepository<Absensi, Long> {

    boolean existsByEnrollmentIdAndTanggal(Long enrollmentId, LocalDate tanggal);

    List<Absensi> findByEnrollmentId(Long enrollmentId);

    List<Absensi> findByEnrollmentKelasIdAndTanggal(Long kelasId, LocalDate tanggal);
}
