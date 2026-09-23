package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.GuruRequestDTO;
import com.portofolio.LMS.dto.GuruResponseDTO;

import java.util.List;

public interface GuruService {

    GuruResponseDTO createGuru(GuruRequestDTO request);

    GuruResponseDTO getGuruById(Long id);

    List<GuruResponseDTO> getAllGuru();

    GuruResponseDTO updateGuru(Long id, GuruRequestDTO request);

    void deleteGuru(Long id);
}
