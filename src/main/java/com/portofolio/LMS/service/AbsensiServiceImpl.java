package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.AbsensiRequestDTO;
import com.portofolio.LMS.dto.AbsensiResponseDTO;
import com.portofolio.LMS.exception.DuplicateResourceException;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.Absensi;
import com.portofolio.LMS.model.Enrollment;
import com.portofolio.LMS.model.Guru;
import com.portofolio.LMS.repository.AbsensiRepository;
import com.portofolio.LMS.repository.EnrollmentRepository;
import com.portofolio.LMS.repository.GuruRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsensiServiceImpl implements AbsensiService{

    private final AbsensiRepository absensiRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GuruRepository guruRepository;

    @Override
    @Transactional
    public AbsensiResponseDTO createAbsensi(AbsensiRequestDTO request) {
        Enrollment enrollment = enrollmentRepository.findById(request.getEnrollmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment tidak ditemukan dengan id: " + request.getEnrollmentId()));

        Guru guru = guruRepository.findById(request.getGuruId())
                .orElseThrow(() -> new ResourceNotFoundException("Guru tidak ditemukan dengan id: " + request.getGuruId()));

        if (absensiRepository.existsByEnrollmentIdAndTanggal(request.getEnrollmentId(), request.getTanggal())) {
            throw new DuplicateResourceException("Absensi untuk siswa ini pada tanggal tersebut sudah tercatat");
        }

        Absensi absensi = Absensi.builder()
                .enrollment(enrollment)
                .guru(guru)
                .tanggal(request.getTanggal())
                .status(request.getStatus())
                .keterangan(request.getKeterangan())
                .build();

        Absensi saved = absensiRepository.save(absensi);
        return AbsensiResponseDTO.fromEntity(saved);
    }

    @Override
    public AbsensiResponseDTO getAbsensiById(Long id) {
        Absensi absensi = absensiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Absensi tidak ditemukan dengan id: " + id));
        return AbsensiResponseDTO.fromEntity(absensi);
    }

    @Override
    public List<AbsensiResponseDTO> getAllAbsensi() {
        return absensiRepository.findAll().stream()
                .map(AbsensiResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<AbsensiResponseDTO> getAbsensiByEnrollmentId(Long enrollmentId) {
        return absensiRepository.findByEnrollmentId(enrollmentId).stream()
                .map(AbsensiResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<AbsensiResponseDTO> getAbsensiByKelasAndTanggal(Long kelasId, LocalDate tanggal) {
        return absensiRepository.findByEnrollmentKelasIdAndTanggal(kelasId, tanggal).stream()
                .map(AbsensiResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public AbsensiResponseDTO updateAbsensi(Long id, AbsensiRequestDTO request) {
        Absensi absensi = absensiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Absensi tidak ditemukan dengan id: " + id));

        absensi.setStatus(request.getStatus());
        absensi.setKeterangan(request.getKeterangan());

        Absensi updated = absensiRepository.save(absensi);
        return AbsensiResponseDTO.fromEntity(updated);
    }

    @Override
    @Transactional
    public void deleteAbsensi(Long id) {
        if (!absensiRepository.existsById(id)) {
            throw new ResourceNotFoundException("Absensi tidak ditemukan dengan id: " + id);
        }
        absensiRepository.deleteById(id);
    }
}
