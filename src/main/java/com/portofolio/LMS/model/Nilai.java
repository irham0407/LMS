package com.portofolio.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "nilai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nilai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "guru_id", nullable = false)
    private Guru guru;

    @Column(name = "mata_pelajaran", nullable = false)
    private String mataPelajaran;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JenisNilai jenis;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal nilai;

    @Column(nullable = false)
    private LocalDate tanggal;

    @Column(columnDefinition = "TEXT")
    private String keterangan;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
