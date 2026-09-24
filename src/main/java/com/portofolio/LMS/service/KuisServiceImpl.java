package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.KuisRequestDTO;
import com.portofolio.LMS.dto.KuisResponseDTO;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.Kuis;
import com.portofolio.LMS.model.Materi;
import com.portofolio.LMS.repository.KuisRepository;
import com.portofolio.LMS.repository.MateriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KuisServiceImpl implements KuisService {

    private final KuisRepository kuisRepository;
    private final MateriRepository materiRepository;

    @Override
    @Transactional
    public KuisResponseDTO createKuis(KuisRequestDTO request) {
        Materi materi = materiRepository.findById(request.getMateriId())
                .orElseThrow(() -> new ResourceNotFoundException("Materi tidak ditemukan dengan id: " + request.getMateriId()));

        Kuis kuis = Kuis.builder()
                .materi(materi)
                .judul(request.getJudul())
                .deskripsi(request.getDeskripsi())
                .build();

        return KuisResponseDTO.fromEntity(kuisRepository.save(kuis));
    }

    @Override
    public KuisResponseDTO getKuisById(Long id) {
        Kuis kuis = kuisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kuis tidak ditemukan dengan id: " + id));
        return KuisResponseDTO.fromEntity(kuis);
    }

    @Override
    public List<KuisResponseDTO> getKuisByMateriId(Long materiId) {
        return kuisRepository.findByMateriId(materiId).stream().map(KuisResponseDTO::fromEntity).toList();
    }

    @Override
    @Transactional
    public void deleteKuis(Long id) {
        if (!kuisRepository.existsById(id)) {
            throw new ResourceNotFoundException("Kuis tidak ditemukan dengan id: " + id);
        }
        kuisRepository.deleteById(id);
    }
}
