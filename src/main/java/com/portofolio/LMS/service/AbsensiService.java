package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.AbsensiRequestDTO;
import com.portofolio.LMS.dto.AbsensiResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface AbsensiService {

    AbsensiResponseDTO createAbsensi(AbsensiRequestDTO request);

    AbsensiResponseDTO getAbsensiById(Long id);

    List<AbsensiResponseDTO> getAllAbsensi();

    List<AbsensiResponseDTO> getAbsensiByEnrollmentId(Long enrollmentId);

    List<AbsensiResponseDTO> getAbsensiByKelasAndTanggal(Long kelasId, LocalDate tanggal);

    AbsensiResponseDTO updateAbsensi(Long id, AbsensiRequestDTO request);

    void deleteAbsensi(Long id);
}
