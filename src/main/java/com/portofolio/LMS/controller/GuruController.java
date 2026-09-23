package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.GuruRequestDTO;
import com.portofolio.LMS.dto.GuruResponseDTO;
import com.portofolio.LMS.service.GuruService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guru")
@RequiredArgsConstructor
public class GuruController {

    private final GuruService guruService;

    @PostMapping
    public ResponseEntity<GuruResponseDTO> createGuru(@Valid @RequestBody GuruRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guruService.createGuru(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuruResponseDTO> getGuruById(@PathVariable Long id) {
        return ResponseEntity.ok(guruService.getGuruById(id));
    }

    @GetMapping
    public ResponseEntity<List<GuruResponseDTO>> getAllGuru() {
        return ResponseEntity.ok(guruService.getAllGuru());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuruResponseDTO> updateGuru(@PathVariable Long id, @Valid @RequestBody GuruRequestDTO request) {
        return ResponseEntity.ok(guruService.updateGuru(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuru(@PathVariable Long id) {
        guruService.deleteGuru(id);
        return ResponseEntity.noContent().build();
    }
}
