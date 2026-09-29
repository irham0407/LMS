package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.NilaiAkhirDTO;
import com.portofolio.LMS.dto.NilaiRequestDTO;
import com.portofolio.LMS.dto.NilaiResponseDTO;
import com.portofolio.LMS.service.NilaiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nilai")
@RequiredArgsConstructor
public class NilaiController {

    private final NilaiService nilaiService;

    @PostMapping
    public ResponseEntity<NilaiResponseDTO> createNilai(@Valid @RequestBody NilaiRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nilaiService.createNilai(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NilaiResponseDTO> getNilaiById(@PathVariable Long id) {
        return ResponseEntity.ok(nilaiService.getNilaiById(id));
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<List<NilaiResponseDTO>> getNilaiByEnrollmentId(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(nilaiService.getNilaiByEnrollmentId(enrollmentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NilaiResponseDTO> updateNilai(@PathVariable Long id, @Valid @RequestBody NilaiRequestDTO request) {
        return ResponseEntity.ok(nilaiService.updateNilai(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNilai(@PathVariable Long id) {
        nilaiService.deleteNilai(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/akhir")
    public ResponseEntity<NilaiAkhirDTO> hitungNilaiAkhir(
            @RequestParam Long enrollmentId,
            @RequestParam String mataPelajaran) {
        return ResponseEntity.ok(nilaiService.hitungNilaiAkhir(enrollmentId, mataPelajaran));
    }
}
