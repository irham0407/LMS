package com.portofolio.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bobot_penilaian")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BobotPenilaian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mata_pelajaran", nullable = false)
    private String mataPelajaran;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JenisNilai jenis;

    @Column(nullable = false)
    private Integer bobot;

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
