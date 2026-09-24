package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.HasilKuisResponseDTO;
import com.portofolio.LMS.dto.SubmitKuisRequestDTO;

import java.util.List;

public interface HasilKuisService {

    HasilKuisResponseDTO submitKuis(SubmitKuisRequestDTO request);
    List<HasilKuisResponseDTO> getHasilByKuisId(Long kuisId);
    List<HasilKuisResponseDTO> getHasilBySiswaId(Long siswaId);
}
