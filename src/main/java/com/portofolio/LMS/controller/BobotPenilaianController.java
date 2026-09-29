package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.BobotPenilaianRequestDTO;
import com.portofolio.LMS.dto.BobotPenilaianResponseDTO;
import com.portofolio.LMS.service.BobotPenilaianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bobot-penilaian")
@RequiredArgsConstructor
public class BobotPenilaianController {

    private final BobotPenilaianService bobotPenilaianService;

    @PostMapping
    public ResponseEntity<BobotPenilaianResponseDTO> createOrUpdateBobot(@Valid @RequestBody BobotPenilaianRequestDTO request) {
        return ResponseEntity.ok(bobotPenilaianService.createOrUpdateBobot(request));
    }

    @GetMapping
    public ResponseEntity<List<BobotPenilaianResponseDTO>> getBobotByMataPelajaran(@RequestParam String mataPelajaran) {
        return ResponseEntity.ok(bobotPenilaianService.getBobotByMataPelajaran(mataPelajaran));
    }
}
