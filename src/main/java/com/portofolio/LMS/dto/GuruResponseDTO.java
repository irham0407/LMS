package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Guru;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuruResponseDTO {

    private Long id;
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private String nikNis;
    private String mataPelajaran;
    private String spesialisasi;
    private String noTelepon;
    private String alamat;
    private LocalDate tanggalBergabung;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GuruResponseDTO fromEntity(Guru guru) {
        return GuruResponseDTO.builder()
                .id(guru.getId())
                .userId(guru.getUser().getId())
                .username(guru.getUser().getUsername())
                .fullName(guru.getUser().getFullName())
                .email(guru.getUser().getEmail())
                .nikNis(guru.getUser().getNikNis())
                .mataPelajaran(guru.getMataPelajaran())
                .spesialisasi(guru.getSpesialisasi())
                .noTelepon(guru.getNoTelepon())
                .alamat(guru.getAlamat())
                .tanggalBergabung(guru.getTanggalBergabung())
                .createdAt(guru.getCreatedAt())
                .updatedAt(guru.getUpdatedAt())
                .build();
    }
}
