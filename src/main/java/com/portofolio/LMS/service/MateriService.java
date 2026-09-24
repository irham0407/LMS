package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.MateriRequestDTO;
import com.portofolio.LMS.dto.MateriResponseDTO;

import java.util.List;

public interface MateriService {

    MateriResponseDTO createMateri(MateriRequestDTO request);
    MateriResponseDTO getMateriById(Long id);
    List<MateriResponseDTO> getAllMateri();
    List<MateriResponseDTO> getMateriByMataPelajaran(String mataPelajaran);
    MateriResponseDTO updateMateri(Long id, MateriRequestDTO request);
    void deleteMateri(Long id);
}
