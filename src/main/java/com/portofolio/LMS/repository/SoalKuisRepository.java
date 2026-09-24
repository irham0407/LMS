package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.SoalKuis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoalKuisRepository extends JpaRepository<SoalKuis, Long> {

    List<SoalKuis> findByKuisId(Long kuisId);
}
