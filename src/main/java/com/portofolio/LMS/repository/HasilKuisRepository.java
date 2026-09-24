package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.HasilKuis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HasilKuisRepository extends JpaRepository<HasilKuis, Long> {

    boolean existsByKuisIdAndSiswaId(Long kuisId, Long siswaId);
    Optional<HasilKuis> findByKuisIdAndSiswaId(Long kuisId, Long siswaId);
    List<HasilKuis> findByKuisId(Long kuisId);
    List<HasilKuis> findBySiswaId(Long siswaId);
}
