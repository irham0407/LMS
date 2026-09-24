package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.SoalKuisRequestDTO;
import com.portofolio.LMS.dto.SoalKuisResponseDTO;

import java.util.List;

public interface SoalKuisService {

    SoalKuisResponseDTO createSoal(SoalKuisRequestDTO request);
    List<SoalKuisResponseDTO> getSoalByKuisId(Long kuisId);
    List<SoalKuisResponseDTO> getSoalByKuisIdForSiswa(Long kuisId); // tanpa kunci jawaban
    void deleteSoal(Long id);
}
