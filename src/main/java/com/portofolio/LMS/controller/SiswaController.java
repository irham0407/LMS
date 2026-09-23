package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.SiswaRequestDTO;
import com.portofolio.LMS.dto.SiswaResponseDTO;
import com.portofolio.LMS.service.SiswaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/siswa")
@RequiredArgsConstructor
public class SiswaController {

    private final SiswaService siswaService;

    @PostMapping
    public ResponseEntity<SiswaResponseDTO> createSiswa(@Valid @RequestBody SiswaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(siswaService.createSiswa(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiswaResponseDTO> getSiswaById(@PathVariable Long id) {
        return ResponseEntity.ok(siswaService.getSiswaById(id));
    }

    @GetMapping
    public ResponseEntity<List<SiswaResponseDTO>> getAllSiswa() {
        return ResponseEntity.ok(siswaService.getAllSiswa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiswaResponseDTO> updateSiswa(@PathVariable Long id, @Valid @RequestBody SiswaRequestDTO request) {
        return ResponseEntity.ok(siswaService.updateSiswa(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSiswa(@PathVariable Long id) {
        siswaService.deleteSiswa(id);
        return ResponseEntity.noContent().build();
    }
}
