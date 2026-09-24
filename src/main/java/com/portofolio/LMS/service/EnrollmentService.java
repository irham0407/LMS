package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.EnrollmentRequestDTO;
import com.portofolio.LMS.dto.EnrollmentResponseDTO;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO request);

    EnrollmentResponseDTO getEnrollmentById(Long id);

    List<EnrollmentResponseDTO> getAllEnrollment();

    List<EnrollmentResponseDTO> getEnrollmentByKelasId(Long kelasId);

    void deleteEnrollment(Long id);
}
