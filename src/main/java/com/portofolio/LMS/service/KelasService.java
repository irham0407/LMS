package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.KelasRequestDTO;
import com.portofolio.LMS.dto.KelasResponseDTO;

import java.util.List;

public interface KelasService {

    KelasResponseDTO createKelas(KelasRequestDTO request);

    KelasResponseDTO getKelasById(Long id);

    List<KelasResponseDTO> getAllKelas();

    KelasResponseDTO updateKelas(Long id, KelasRequestDTO request);

    void deleteKelas(Long id);
}
