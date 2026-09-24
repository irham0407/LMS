package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>  {

    boolean existsBySiswaIdAndKelasIdAndTahunAjaran(Long siswaId, Long kelasId, String tahunAjaran);

    List<Enrollment> findByKelasId(Long kelasId);

    List<Enrollment> findBySiswaId(Long siswaId);
}
