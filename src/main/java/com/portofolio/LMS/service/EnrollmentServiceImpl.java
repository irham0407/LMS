package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.EnrollmentRequestDTO;
import com.portofolio.LMS.dto.EnrollmentResponseDTO;
import com.portofolio.LMS.exception.DuplicateResourceException;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.Enrollment;
import com.portofolio.LMS.model.Kelas;
import com.portofolio.LMS.model.Siswa;
import com.portofolio.LMS.repository.EnrollmentRepository;
import com.portofolio.LMS.repository.KelasRepository;
import com.portofolio.LMS.repository.SiswaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final SiswaRepository siswaRepository;
    private final KelasRepository kelasRepository;

    @Override
    @Transactional
    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO request) {
        Siswa siswa = siswaRepository.findById(request.getSiswaId())
                .orElseThrow(() -> new ResourceNotFoundException("Siswa tidak ditemukan dengan id: " + request.getSiswaId()));

        Kelas kelas = kelasRepository.findById(request.getKelasId())
                .orElseThrow(() -> new ResourceNotFoundException("Kelas tidak ditemukan dengan id: " + request.getKelasId()));

        if (enrollmentRepository.existsBySiswaIdAndKelasIdAndTahunAjaran(
                request.getSiswaId(), request.getKelasId(), request.getTahunAjaran())) {
            throw new DuplicateResourceException("Siswa ini sudah terdaftar di kelas dan tahun ajaran yang sama");
        }

        Enrollment enrollment = Enrollment.builder()
                .siswa(siswa)
                .kelas(kelas)
                .tahunAjaran(request.getTahunAjaran())
                .tanggalMasuk(request.getTanggalMasuk())
                .status(Enrollment.Status.AKTIF)
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);
        return EnrollmentResponseDTO.fromEntity(saved);
    }

    @Override
    public EnrollmentResponseDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment tidak ditemukan dengan id: " + id));
        return EnrollmentResponseDTO.fromEntity(enrollment);
    }

    @Override
    public List<EnrollmentResponseDTO> getAllEnrollment() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentByKelasId(Long kelasId) {
        return enrollmentRepository.findByKelasId(kelasId).stream()
                .map(EnrollmentResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment tidak ditemukan dengan id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }
}
