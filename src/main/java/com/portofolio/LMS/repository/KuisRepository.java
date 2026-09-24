package com.portofolio.LMS.repository;

import com.portofolio.LMS.model.Kuis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KuisRepository extends JpaRepository<Kuis, Long> {

    List<Kuis> findByMateriId(Long materiId);
}
