package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.HasilKuisResponseDTO;
import com.portofolio.LMS.dto.SubmitKuisRequestDTO;
import com.portofolio.LMS.service.HasilKuisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hasil-kuis")
@RequiredArgsConstructor
public class HasilKuisController {

    private final HasilKuisService hasilKuisService;

    @PostMapping("/submit")
    public ResponseEntity<HasilKuisResponseDTO> submitKuis(@Valid @RequestBody SubmitKuisRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hasilKuisService.submitKuis(request));
    }

    @GetMapping("/kuis/{kuisId}")
    public ResponseEntity<List<HasilKuisResponseDTO>> getHasilByKuisId(@PathVariable Long kuisId) {
        return ResponseEntity.ok(hasilKuisService.getHasilByKuisId(kuisId));
    }

    @GetMapping("/siswa/{siswaId}")
    public ResponseEntity<List<HasilKuisResponseDTO>> getHasilBySiswaId(@PathVariable Long siswaId) {
        return ResponseEntity.ok(hasilKuisService.getHasilBySiswaId(siswaId));
    }
}
