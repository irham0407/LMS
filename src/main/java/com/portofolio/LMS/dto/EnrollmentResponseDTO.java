package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Enrollment;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponseDTO {

    private Long id;
    private Long siswaId;
    private String siswaNama;
    private String siswaNis;
    private Long kelasId;
    private String namaKelas;
    private String tahunAjaran;
    private LocalDate tanggalMasuk;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static EnrollmentResponseDTO fromEntity(Enrollment enrollment) {
        return EnrollmentResponseDTO.builder()
                .id(enrollment.getId())
                .siswaId(enrollment.getSiswa().getId())
                .siswaNama(enrollment.getSiswa().getFullName())
                .siswaNis(enrollment.getSiswa().getNis())
                .kelasId(enrollment.getKelas().getId())
                .namaKelas(enrollment.getKelas().getNamaKelas())
                .tahunAjaran(enrollment.getTahunAjaran())
                .tanggalMasuk(enrollment.getTanggalMasuk())
                .status(enrollment.getStatus().name())
                .createdAt(enrollment.getCreatedAt())
                .updatedAt(enrollment.getUpdatedAt())
                .build();
    }
}
