package com.portofolio.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hasil_kuis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HasilKuis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kuis_id", nullable = false)
    private Kuis kuis;

    @ManyToOne
    @JoinColumn(name = "siswa_id", nullable = false)
    private Siswa siswa;

    @Column(name = "skor_total", nullable = false)
    private Integer skorTotal;

    @Column(name = "tanggal_pengerjaan", nullable = false)
    private LocalDateTime tanggalPengerjaan;

    @PrePersist
    protected void onCreate() {
        this.tanggalPengerjaan = LocalDateTime.now();
    }
}
