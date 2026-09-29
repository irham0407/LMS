package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.BobotPenilaianRequestDTO;
import com.portofolio.LMS.dto.BobotPenilaianResponseDTO;

import java.util.List;

public interface BobotPenilaianService {

    BobotPenilaianResponseDTO createOrUpdateBobot(BobotPenilaianRequestDTO request);
    List<BobotPenilaianResponseDTO> getBobotByMataPelajaran(String mataPelajaran);
}
