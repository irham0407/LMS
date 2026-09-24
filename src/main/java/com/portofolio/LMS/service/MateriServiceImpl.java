package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.MateriRequestDTO;
import com.portofolio.LMS.dto.MateriResponseDTO;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.Guru;
import com.portofolio.LMS.model.Materi;
import com.portofolio.LMS.repository.GuruRepository;
import com.portofolio.LMS.repository.MateriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriServiceImpl implements MateriService {

    private final MateriRepository materiRepository;
    private final GuruRepository guruRepository;

    @Override
    @Transactional
    public MateriResponseDTO createMateri(MateriRequestDTO request) {
        Guru guru = guruRepository.findById(request.getGuruId())
                .orElseThrow(() -> new ResourceNotFoundException("Guru tidak ditemukan dengan id: " + request.getGuruId()));

        Materi materi = Materi.builder()
                .guru(guru)
                .mataPelajaran(request.getMataPelajaran())
                .judul(request.getJudul())
                .deskripsi(request.getDeskripsi())
                .fileUrl(request.getFileUrl())
                .build();

        return MateriResponseDTO.fromEntity(materiRepository.save(materi));
    }

    @Override
    public MateriResponseDTO getMateriById(Long id) {
        Materi materi = materiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Materi tidak ditemukan dengan id: " + id));
        return MateriResponseDTO.fromEntity(materi);
    }

    @Override
    public List<MateriResponseDTO> getAllMateri() {
        return materiRepository.findAll().stream().map(MateriResponseDTO::fromEntity).toList();
    }

    @Override
    public List<MateriResponseDTO> getMateriByMataPelajaran(String mataPelajaran) {
        return materiRepository.findByMataPelajaran(mataPelajaran).stream()
                .map(MateriResponseDTO::fromEntity).toList();
    }

    @Override
    @Transactional
    public MateriResponseDTO updateMateri(Long id, MateriRequestDTO request) {
        Materi materi = materiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Materi tidak ditemukan dengan id: " + id));

        materi.setMataPelajaran(request.getMataPelajaran());
        materi.setJudul(request.getJudul());
        materi.setDeskripsi(request.getDeskripsi());
        materi.setFileUrl(request.getFileUrl());

        return MateriResponseDTO.fromEntity(materiRepository.save(materi));
    }

    @Override
    @Transactional
    public void deleteMateri(Long id) {
        if (!materiRepository.existsById(id)) {
            throw new ResourceNotFoundException("Materi tidak ditemukan dengan id: " + id);
        }
        materiRepository.deleteById(id);
    }
}
