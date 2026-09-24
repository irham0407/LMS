package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.HasilKuisResponseDTO;
import com.portofolio.LMS.dto.JawabanItemDTO;
import com.portofolio.LMS.dto.SubmitKuisRequestDTO;
import com.portofolio.LMS.exception.DuplicateResourceException;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.HasilKuis;
import com.portofolio.LMS.model.Kuis;
import com.portofolio.LMS.model.Siswa;
import com.portofolio.LMS.model.SoalKuis;
import com.portofolio.LMS.repository.HasilKuisRepository;
import com.portofolio.LMS.repository.KuisRepository;
import com.portofolio.LMS.repository.SiswaRepository;
import com.portofolio.LMS.repository.SoalKuisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HasilKuisServiceImpl implements HasilKuisService {

    private final HasilKuisRepository hasilKuisRepository;
    private final KuisRepository kuisRepository;
    private final SiswaRepository siswaRepository;
    private final SoalKuisRepository soalKuisRepository;

    @Override
    @Transactional
    public HasilKuisResponseDTO submitKuis(SubmitKuisRequestDTO request) {
        Kuis kuis = kuisRepository.findById(request.getKuisId())
                .orElseThrow(() -> new ResourceNotFoundException("Kuis tidak ditemukan dengan id: " + request.getKuisId()));

        Siswa siswa = siswaRepository.findById(request.getSiswaId())
                .orElseThrow(() -> new ResourceNotFoundException("Siswa tidak ditemukan dengan id: " + request.getSiswaId()));

        if (hasilKuisRepository.existsByKuisIdAndSiswaId(request.getKuisId(), request.getSiswaId())) {
            throw new DuplicateResourceException("Siswa ini sudah pernah mengerjakan kuis ini");
        }

        List<SoalKuis> semuaSoal = soalKuisRepository.findByKuisId(request.getKuisId());
        Map<Long, SoalKuis> soalById = semuaSoal.stream()
                .collect(Collectors.toMap(SoalKuis::getId, s -> s));

        int totalSkor = 0;
        for (JawabanItemDTO jawaban : request.getJawaban()) {
            SoalKuis soal = soalById.get(jawaban.getSoalId());
            if (soal == null) {
                throw new ResourceNotFoundException("Soal tidak ditemukan dengan id: " + jawaban.getSoalId());
            }
            if (soal.getJawabanBenar().equalsIgnoreCase(jawaban.getJawabanPilihan())) {
                totalSkor += soal.getSkor();
            }
        }

        HasilKuis hasilKuis = HasilKuis.builder()
                .kuis(kuis)
                .siswa(siswa)
                .skorTotal(totalSkor)
                .build();

        return HasilKuisResponseDTO.fromEntity(hasilKuisRepository.save(hasilKuis));
    }

    @Override
    public List<HasilKuisResponseDTO> getHasilByKuisId(Long kuisId) {
        return hasilKuisRepository.findByKuisId(kuisId).stream()
                .map(HasilKuisResponseDTO::fromEntity).toList();
    }

    @Override
    public List<HasilKuisResponseDTO> getHasilBySiswaId(Long siswaId) {
        return hasilKuisRepository.findBySiswaId(siswaId).stream()
                .map(HasilKuisResponseDTO::fromEntity).toList();
    }
}
