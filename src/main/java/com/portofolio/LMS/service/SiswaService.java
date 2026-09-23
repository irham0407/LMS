package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.SiswaRequestDTO;
import com.portofolio.LMS.dto.SiswaResponseDTO;

import java.util.List;

public interface SiswaService {

    SiswaResponseDTO createSiswa(SiswaRequestDTO request);

    SiswaResponseDTO getSiswaById(Long id);

    List<SiswaResponseDTO> getAllSiswa();

    SiswaResponseDTO updateSiswa(Long id, SiswaRequestDTO request);

    void deleteSiswa(Long id);

}
