package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.KelasRequestDTO;
import com.portofolio.LMS.dto.KelasResponseDTO;
import com.portofolio.LMS.exception.DuplicateResourceException;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.Guru;
import com.portofolio.LMS.model.Kelas;
import com.portofolio.LMS.repository.GuruRepository;
import com.portofolio.LMS.repository.KelasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KelasServiceImpl implements KelasService {

    private final KelasRepository kelasRepository;
    private final GuruRepository guruRepository;

    @Override
    @Transactional
    public KelasResponseDTO createKelas(KelasRequestDTO request) {
        if (kelasRepository.existsByNamaKelas(request.getNamaKelas())) {
            throw new DuplicateResourceException("Nama kelas sudah terdaftar: " + request.getNamaKelas());
        }

        Guru waliKelas = null;
        if (request.getWaliKelasId() != null) {
            waliKelas = guruRepository.findById(request.getWaliKelasId())
                    .orElseThrow(() -> new ResourceNotFoundException("Guru tidak ditemukan dengan id: " + request.getWaliKelasId()));
        }

        Kelas kelas = Kelas.builder()
                .namaKelas(request.getNamaKelas())
                .tingkat(request.getTingkat())
                .waliKelas(waliKelas)
                .tahunAjaran(request.getTahunAjaran())
                .build();

        Kelas saved = kelasRepository.save(kelas);
        return KelasResponseDTO.fromEntity(saved);
    }

    @Override
    public KelasResponseDTO getKelasById(Long id) {
        Kelas kelas = kelasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kelas tidak ditemukan dengan id: " + id));
        return KelasResponseDTO.fromEntity(kelas);
    }

    @Override
    public List<KelasResponseDTO> getAllKelas() {
        return kelasRepository.findAll().stream()
                .map(KelasResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public KelasResponseDTO updateKelas(Long id, KelasRequestDTO request) {
        Kelas kelas = kelasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kelas tidak ditemukan dengan id: " + id));

        Guru waliKelas = null;
        if (request.getWaliKelasId() != null) {
            waliKelas = guruRepository.findById(request.getWaliKelasId())
                    .orElseThrow(() -> new ResourceNotFoundException("Guru tidak ditemukan dengan id: " + request.getWaliKelasId()));
        }

        kelas.setNamaKelas(request.getNamaKelas());
        kelas.setTingkat(request.getTingkat());
        kelas.setWaliKelas(waliKelas);
        kelas.setTahunAjaran(request.getTahunAjaran());

        Kelas updated = kelasRepository.save(kelas);
        return KelasResponseDTO.fromEntity(updated);
    }

    @Override
    @Transactional
    public void deleteKelas(Long id) {
        if (!kelasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Kelas tidak ditemukan dengan id: " + id);
        }
        kelasRepository.deleteById(id);
    }
}
