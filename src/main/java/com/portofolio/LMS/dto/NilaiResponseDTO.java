package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Nilai;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NilaiResponseDTO {

    private Long id;
    private Long enrollmentId;
    private String siswaNama;
    private String siswaNis;
    private String namaKelas;
    private Long guruId;
    private String guruNama;
    private String mataPelajaran;
    private String jenis;
    private BigDecimal nilai;
    private LocalDate tanggal;
    private String keterangan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static NilaiResponseDTO fromEntity(Nilai n) {
        return NilaiResponseDTO.builder()
                .id(n.getId())
                .enrollmentId(n.getEnrollment().getId())
                .siswaNama(n.getEnrollment().getSiswa().getFullName())
                .siswaNis(n.getEnrollment().getSiswa().getNis())
                .namaKelas(n.getEnrollment().getKelas().getNamaKelas())
                .guruId(n.getGuru().getId())
                .guruNama(n.getGuru().getFullName())
                .mataPelajaran(n.getMataPelajaran())
                .jenis(n.getJenis().name())
                .nilai(n.getNilai())
                .tanggal(n.getTanggal())
                .keterangan(n.getKeterangan())
                .createdAt(n.getCreatedAt())
                .updatedAt(n.getUpdatedAt())
                .build();
    }
}
