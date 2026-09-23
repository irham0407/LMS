package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.GuruRequestDTO;
import com.portofolio.LMS.dto.GuruResponseDTO;
import com.portofolio.LMS.exception.DuplicateResourceException;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.Guru;
import com.portofolio.LMS.model.User;
import com.portofolio.LMS.repository.GuruRepository;
import com.portofolio.LMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuruServiceImpl implements GuruService{

    private final GuruRepository guruRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public GuruResponseDTO createGuru(GuruRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan dengan id: " + request.getUserId()));

        if (user.getRole() != User.Role.GURU && user.getRole() != User.Role.ADMIN) {
            throw new IllegalArgumentException("User dengan id " + request.getUserId() + " harus berrole GURU atau ADMIN");
        }

        if (guruRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateResourceException("User ini sudah terdaftar sebagai Guru");
        }

        Guru guru = Guru.builder()
                .user(user)
                .fullName(user.getFullName())   // ditambahkan
                .email(user.getEmail())
                .nik(user.getNikNis())
                .mataPelajaran(request.getMataPelajaran())
                .spesialisasi(request.getSpesialisasi())
                .noTelepon(request.getNoTelepon())
                .alamat(request.getAlamat())
                .tanggalBergabung(request.getTanggalBergabung())
                .build();

        Guru saved = guruRepository.save(guru);
        return GuruResponseDTO.fromEntity(saved);
    }

    @Override
    public GuruResponseDTO getGuruById(Long id) {
        Guru guru = guruRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guru tidak ditemukan dengan id: " + id));
        return GuruResponseDTO.fromEntity(guru);
    }

    @Override
    public List<GuruResponseDTO> getAllGuru() {
        return guruRepository.findAll().stream()
                .map(GuruResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public GuruResponseDTO updateGuru(Long id, @NonNull GuruRequestDTO request) {
        Guru guru = guruRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guru tidak ditemukan dengan id: " + id));

        guru.setMataPelajaran(request.getMataPelajaran());
        guru.setSpesialisasi(request.getSpesialisasi());
        guru.setNoTelepon(request.getNoTelepon());
        guru.setAlamat(request.getAlamat());
        guru.setTanggalBergabung(request.getTanggalBergabung());

        Guru updated = guruRepository.save(guru);
        return GuruResponseDTO.fromEntity(updated);
    }

    @Override
    @Transactional
    public void deleteGuru(Long id) {
        if (!guruRepository.existsById(id)) {
            throw new ResourceNotFoundException("Guru tidak ditemukan dengan id: " + id);
        }
        guruRepository.deleteById(id);
    }
}
