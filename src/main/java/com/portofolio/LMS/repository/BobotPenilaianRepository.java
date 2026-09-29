package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.BobotPenilaian;
import com.portofolio.LMS.model.JenisNilai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BobotPenilaianRepository extends JpaRepository <BobotPenilaian, Long> {

    List<BobotPenilaian> findByMataPelajaran(String mataPelajaran);
    Optional<BobotPenilaian> findByMataPelajaranAndJenis(String mataPelajaran, JenisNilai jenis);
    boolean existsByMataPelajaranAndJenis(String mataPelajaran, JenisNilai jenis);
}
