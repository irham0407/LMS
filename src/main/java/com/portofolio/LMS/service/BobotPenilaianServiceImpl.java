package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.BobotPenilaianRequestDTO;
import com.portofolio.LMS.dto.BobotPenilaianResponseDTO;
import com.portofolio.LMS.model.BobotPenilaian;
import com.portofolio.LMS.repository.BobotPenilaianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BobotPenilaianServiceImpl implements BobotPenilaianService {

    private final BobotPenilaianRepository bobotPenilaianRepository;

    @Override
    @Transactional
    public BobotPenilaianResponseDTO createOrUpdateBobot(BobotPenilaianRequestDTO request) {
        BobotPenilaian bobot = bobotPenilaianRepository
                .findByMataPelajaranAndJenis(request.getMataPelajaran(), request.getJenis())
                .orElse(BobotPenilaian.builder()
                        .mataPelajaran(request.getMataPelajaran())
                        .jenis(request.getJenis())
                        .build());

        bobot.setBobot(request.getBobot());

        return BobotPenilaianResponseDTO.fromEntity(bobotPenilaianRepository.save(bobot));
    }

    @Override
    public List<BobotPenilaianResponseDTO> getBobotByMataPelajaran(String mataPelajaran) {
        return bobotPenilaianRepository.findByMataPelajaran(mataPelajaran).stream()
                .map(BobotPenilaianResponseDTO::fromEntity).toList();
    }

}
