package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.MateriRequestDTO;
import com.portofolio.LMS.dto.MateriResponseDTO;
import com.portofolio.LMS.service.MateriService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materi")
@RequiredArgsConstructor
public class MateriController {

    private final MateriService materiService;

    @PostMapping
    public ResponseEntity<MateriResponseDTO> createMateri(@Valid @RequestBody MateriRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materiService.createMateri(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriResponseDTO> getMateriById(@PathVariable Long id) {
        return ResponseEntity.ok(materiService.getMateriById(id));
    }

    @GetMapping
    public ResponseEntity<List<MateriResponseDTO>> getAllMateri(@RequestParam(required = false) String mataPelajaran) {
        if (mataPelajaran != null) {
            return ResponseEntity.ok(materiService.getMateriByMataPelajaran(mataPelajaran));
        }
        return ResponseEntity.ok(materiService.getAllMateri());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriResponseDTO> updateMateri(@PathVariable Long id, @Valid @RequestBody MateriRequestDTO request) {
        return ResponseEntity.ok(materiService.updateMateri(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateri(@PathVariable Long id) {
        materiService.deleteMateri(id);
        return ResponseEntity.noContent().build();
    }
}
