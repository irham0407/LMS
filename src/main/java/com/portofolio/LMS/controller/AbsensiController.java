package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.AbsensiRequestDTO;
import com.portofolio.LMS.dto.AbsensiResponseDTO;
import com.portofolio.LMS.service.AbsensiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/absensi")
@RequiredArgsConstructor
public class AbsensiController {

    private final AbsensiService absensiService;

    @PostMapping
    public ResponseEntity<AbsensiResponseDTO> createAbsensi(@Valid @RequestBody AbsensiRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(absensiService.createAbsensi(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbsensiResponseDTO> getAbsensiById(@PathVariable Long id) {
        return ResponseEntity.ok(absensiService.getAbsensiById(id));
    }

    @GetMapping
    public ResponseEntity<List<AbsensiResponseDTO>> getAllAbsensi() {
        return ResponseEntity.ok(absensiService.getAllAbsensi());
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<List<AbsensiResponseDTO>> getAbsensiByEnrollmentId(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(absensiService.getAbsensiByEnrollmentId(enrollmentId));
    }

    @GetMapping("/kelas/{kelasId}")
    public ResponseEntity<List<AbsensiResponseDTO>> getAbsensiByKelasAndTanggal(
            @PathVariable Long kelasId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tanggal) {
        return ResponseEntity.ok(absensiService.getAbsensiByKelasAndTanggal(kelasId, tanggal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbsensiResponseDTO> updateAbsensi(@PathVariable Long id, @Valid @RequestBody AbsensiRequestDTO request) {
        return ResponseEntity.ok(absensiService.updateAbsensi(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsensi(@PathVariable Long id) {
        absensiService.deleteAbsensi(id);
        return ResponseEntity.noContent().build();
    }
}
