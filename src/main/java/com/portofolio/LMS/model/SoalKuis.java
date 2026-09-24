package com.portofolio.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "soal_kuis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoalKuis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kuis_id", nullable = false)
    private Kuis kuis;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String pertanyaan;

    @Column(name = "pilihan_a", nullable = false)
    private String pilihanA;

    @Column(name = "pilihan_b", nullable = false)
    private String pilihanB;

    @Column(name = "pilihan_c", nullable = false)
    private String pilihanC;

    @Column(name = "pilihan_d", nullable = false)
    private String pilihanD;

    @Column(name = "jawaban_benar", nullable = false, length = 1)
    private String jawabanBenar; // "A" / "B" / "C" / "D"

    @Column(nullable = false)
    private Integer skor;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.skor == null) this.skor = 10;
    }
}
