package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.SoalKuisRequestDTO;
import com.portofolio.LMS.dto.SoalKuisResponseDTO;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.Kuis;
import com.portofolio.LMS.model.SoalKuis;
import com.portofolio.LMS.repository.KuisRepository;
import com.portofolio.LMS.repository.SoalKuisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoalKuisServiceImpl implements SoalKuisService {

    private final SoalKuisRepository soalKuisRepository;
    private final KuisRepository kuisRepository;

    @Override
    @Transactional
    public SoalKuisResponseDTO createSoal(SoalKuisRequestDTO request) {
        Kuis kuis = kuisRepository.findById(request.getKuisId())
                .orElseThrow(() -> new ResourceNotFoundException("Kuis tidak ditemukan dengan id: " + request.getKuisId()));

        SoalKuis soal = SoalKuis.builder()
                .kuis(kuis)
                .pertanyaan(request.getPertanyaan())
                .pilihanA(request.getPilihanA())
                .pilihanB(request.getPilihanB())
                .pilihanC(request.getPilihanC())
                .pilihanD(request.getPilihanD())
                .jawabanBenar(request.getJawabanBenar())
                .skor(request.getSkor())
                .build();

        return SoalKuisResponseDTO.fromEntity(soalKuisRepository.save(soal));
    }

    @Override
    public List<SoalKuisResponseDTO> getSoalByKuisId(Long kuisId) {
        return soalKuisRepository.findByKuisId(kuisId).stream()
                .map(SoalKuisResponseDTO::fromEntity).toList();
    }

    @Override
    public List<SoalKuisResponseDTO> getSoalByKuisIdForSiswa(Long kuisId) {
        return soalKuisRepository.findByKuisId(kuisId).stream()
                .map(SoalKuisResponseDTO::fromEntityHideAnswer).toList();
    }

    @Override
    @Transactional
    public void deleteSoal(Long id) {
        if (!soalKuisRepository.existsById(id)) {
            throw new ResourceNotFoundException("Soal tidak ditemukan dengan id: " + id);
        }
        soalKuisRepository.deleteById(id);
    }
}
