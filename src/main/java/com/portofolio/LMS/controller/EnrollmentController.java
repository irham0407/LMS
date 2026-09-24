package com.portofolio.LMS.controller;

import com.portofolio.LMS.dto.EnrollmentRequestDTO;
import com.portofolio.LMS.dto.EnrollmentResponseDTO;
import com.portofolio.LMS.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(@Valid @RequestBody EnrollmentRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.createEnrollment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollment() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollment());
    }

    @GetMapping("/kelas/{kelasId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentByKelasId(@PathVariable Long kelasId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentByKelasId(kelasId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
