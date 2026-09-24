package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Kelas;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KelasResponseDTO {

    private Long id;
    private String namaKelas;
    private String tingkat;
    private Long waliKelasId;
    private String waliKelasNama;
    private String tahunAjaran;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static KelasResponseDTO fromEntity(Kelas kelas) {
        return KelasResponseDTO.builder()
                .id(kelas.getId())
                .namaKelas(kelas.getNamaKelas())
                .tingkat(kelas.getTingkat())
                .waliKelasId(kelas.getWaliKelas() != null ? kelas.getWaliKelas().getId() : null)
                .waliKelasNama(kelas.getWaliKelas() != null ? kelas.getWaliKelas().getFullName() : null)
                .tahunAjaran(kelas.getTahunAjaran())
                .createdAt(kelas.getCreatedAt())
                .updatedAt(kelas.getUpdatedAt())
                .build();
    }
}
