package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.SiswaRequestDTO;
import com.portofolio.LMS.dto.SiswaResponseDTO;
import com.portofolio.LMS.exception.DuplicateResourceException;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.Siswa;
import com.portofolio.LMS.model.User;
import com.portofolio.LMS.repository.SiswaRepository;
import com.portofolio.LMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiswaServiceImpl implements SiswaService{


    private final SiswaRepository siswaRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public SiswaResponseDTO createSiswa(SiswaRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan dengan id: " + request.getUserId()));

        if (user.getRole() != User.Role.SISWA) {
            throw new IllegalArgumentException("User dengan id " + request.getUserId() + " harus berrole SISWA");
        }

        if (siswaRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateResourceException("User ini sudah terdaftar sebagai Siswa");
        }

        Siswa siswa = Siswa.builder()
                .user(user)
                .fullName(user.getFullName())
                .email(user.getEmail())
                .nis(user.getNikNis())
                .tempatLahir(request.getTempatLahir())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .alamat(request.getAlamat())
                .noTeleponOrangTua(request.getNoTeleponOrangTua())
                .tanggalMasuk(request.getTanggalMasuk())
                .build();

        Siswa saved = siswaRepository.save(siswa);
        return SiswaResponseDTO.fromEntity(saved);
    }

    @Override
    public SiswaResponseDTO getSiswaById(Long id) {
        Siswa siswa = siswaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Siswa tidak ditemukan dengan id: " + id));
        return SiswaResponseDTO.fromEntity(siswa);
    }

    @Override
    public List<SiswaResponseDTO> getAllSiswa() {
        return siswaRepository.findAll().stream()
                .map(SiswaResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public SiswaResponseDTO updateSiswa(Long id, SiswaRequestDTO request) {
        Siswa siswa = siswaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Siswa tidak ditemukan dengan id: " + id));

        siswa.setTempatLahir(request.getTempatLahir());
        siswa.setTanggalLahir(request.getTanggalLahir());
        siswa.setJenisKelamin(request.getJenisKelamin());
        siswa.setAlamat(request.getAlamat());
        siswa.setNoTeleponOrangTua(request.getNoTeleponOrangTua());
        siswa.setTanggalMasuk(request.getTanggalMasuk());

        Siswa updated = siswaRepository.save(siswa);
        return SiswaResponseDTO.fromEntity(updated);
    }

    @Override
    @Transactional
    public void deleteSiswa(Long id) {
        if (!siswaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Siswa tidak ditemukan dengan id: " + id);
        }
        siswaRepository.deleteById(id);
    }
}
