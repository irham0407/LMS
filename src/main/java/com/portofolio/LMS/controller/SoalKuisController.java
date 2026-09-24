package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.SoalKuisRequestDTO;
import com.portofolio.LMS.dto.SoalKuisResponseDTO;
import com.portofolio.LMS.service.SoalKuisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soal-kuis")
@RequiredArgsConstructor
public class SoalKuisController {

    private final SoalKuisService soalKuisService;

    @PostMapping
    public ResponseEntity<SoalKuisResponseDTO> createSoal(@Valid @RequestBody SoalKuisRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(soalKuisService.createSoal(request));
    }

    // Untuk GURU - lihat soal LENGKAP dengan kunci jawaban
    @GetMapping("/kuis/{kuisId}")
    public ResponseEntity<List<SoalKuisResponseDTO>> getSoalByKuisId(@PathVariable Long kuisId) {
        return ResponseEntity.ok(soalKuisService.getSoalByKuisId(kuisId));
    }

    // Untuk SISWA - lihat soal TANPA kunci jawaban, dipakai saat mengerjakan
    @GetMapping("/kuis/{kuisId}/kerjakan")
    public ResponseEntity<List<SoalKuisResponseDTO>> getSoalUntukDikerjakan(@PathVariable Long kuisId) {
        return ResponseEntity.ok(soalKuisService.getSoalByKuisIdForSiswa(kuisId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoal(@PathVariable Long id) {
        soalKuisService.deleteSoal(id);
        return ResponseEntity.noContent().build();
    }
}
