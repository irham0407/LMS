package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.NilaiAkhirDTO;
import com.portofolio.LMS.dto.NilaiRequestDTO;
import com.portofolio.LMS.dto.NilaiResponseDTO;

import java.util.List;

public interface NilaiService {

    NilaiResponseDTO createNilai(NilaiRequestDTO request);
    NilaiResponseDTO getNilaiById(Long id);
    List<NilaiResponseDTO> getNilaiByEnrollmentId(Long enrollmentId);
    NilaiResponseDTO updateNilai(Long id, NilaiRequestDTO request);
    void deleteNilai(Long id);
    NilaiAkhirDTO hitungNilaiAkhir(Long enrollmentId, String mataPelajaran);
}
