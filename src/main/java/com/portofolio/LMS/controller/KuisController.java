package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.KuisRequestDTO;
import com.portofolio.LMS.dto.KuisResponseDTO;
import com.portofolio.LMS.service.KuisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kuis")
@RequiredArgsConstructor
public class KuisController {

    private final KuisService kuisService;

    @PostMapping
    public ResponseEntity<KuisResponseDTO> createKuis(@Valid @RequestBody KuisRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kuisService.createKuis(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KuisResponseDTO> getKuisById(@PathVariable Long id) {
        return ResponseEntity.ok(kuisService.getKuisById(id));
    }

    @GetMapping("/materi/{materiId}")
    public ResponseEntity<List<KuisResponseDTO>> getKuisByMateriId(@PathVariable Long materiId) {
        return ResponseEntity.ok(kuisService.getKuisByMateriId(materiId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKuis(@PathVariable Long id) {
        kuisService.deleteKuis(id);
        return ResponseEntity.noContent().build();
    }
}
