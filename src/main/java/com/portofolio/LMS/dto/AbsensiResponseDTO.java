package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Absensi;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbsensiResponseDTO {

    private Long id;
    private Long enrollmentId;
    private String siswaNama;
    private String siswaNis;
    private String namaKelas;
    private Long guruId;
    private String guruNama;
    private LocalDate tanggal;
    private String status;
    private String keterangan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AbsensiResponseDTO fromEntity(Absensi absensi) {
        return AbsensiResponseDTO.builder()
                .id(absensi.getId())
                .enrollmentId(absensi.getEnrollment().getId())
                .siswaNama(absensi.getEnrollment().getSiswa().getFullName())
                .siswaNis(absensi.getEnrollment().getSiswa().getNis())
                .namaKelas(absensi.getEnrollment().getKelas().getNamaKelas())
                .guruId(absensi.getGuru().getId())
                .guruNama(absensi.getGuru().getFullName())
                .tanggal(absensi.getTanggal())
                .status(absensi.getStatus().name())
                .keterangan(absensi.getKeterangan())
                .createdAt(absensi.getCreatedAt())
                .updatedAt(absensi.getUpdatedAt())
                .build();
    }
}
