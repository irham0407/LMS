package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.KuisRequestDTO;
import com.portofolio.LMS.dto.KuisResponseDTO;

import java.util.List;

public interface KuisService {

    KuisResponseDTO createKuis(KuisRequestDTO request);
    KuisResponseDTO getKuisById(Long id);
    List<KuisResponseDTO> getKuisByMateriId(Long materiId);
    void deleteKuis(Long id);
}
