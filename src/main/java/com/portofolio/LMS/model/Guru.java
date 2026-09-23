package com.portofolio.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "guru")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nik")
    private String nik;

    @Column(name = "mata_pelajaran", nullable = false)
    private String mataPelajaran;

    @Column(name = "spesialisasi")
    private String spesialisasi;

    @Column(name = "no_telepon")
    private String noTelepon;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "tanggal_bergabung")
    private LocalDate tanggalBergabung;

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
