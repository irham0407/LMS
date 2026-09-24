package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.KelasRequestDTO;
import com.portofolio.LMS.dto.KelasResponseDTO;
import com.portofolio.LMS.service.KelasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kelas")
@RequiredArgsConstructor
public class KelasController {

    private final KelasService kelasService;

    @PostMapping
    public ResponseEntity<KelasResponseDTO> createKelas(@Valid @RequestBody KelasRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kelasService.createKelas(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KelasResponseDTO> getKelasById(@PathVariable Long id) {
        return ResponseEntity.ok(kelasService.getKelasById(id));
    }

    @GetMapping
    public ResponseEntity<List<KelasResponseDTO>> getAllKelas() {
        return ResponseEntity.ok(kelasService.getAllKelas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KelasResponseDTO> updateKelas(@PathVariable Long id, @Valid @RequestBody KelasRequestDTO request) {
        return ResponseEntity.ok(kelasService.updateKelas(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKelas(@PathVariable Long id) {
        kelasService.deleteKelas(id);
        return ResponseEntity.noContent().build();
    }
}
