package com.portofolio.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "kelas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_kelas", nullable = false, unique = true)
    private String namaKelas;

    @Column(name = "tingkat", nullable = false)
    private String tingkat;

    @ManyToOne
    @JoinColumn(name = "wali_kelas_id")
    private Guru waliKelas;

    @Column(name = "tahun_ajaran", nullable = false)
    private String tahunAjaran;

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
