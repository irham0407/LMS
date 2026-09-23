package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Siswa;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiswaResponseDTO {

    private Long id;
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private String nis;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
    private String alamat;
    private String noTeleponOrangTua;
    private LocalDate tanggalMasuk;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static SiswaResponseDTO fromEntity(Siswa siswa) {
        return SiswaResponseDTO.builder()
                .id(siswa.getId())
                .userId(siswa.getUser().getId())
                .username(siswa.getUser().getUsername())
                .fullName(siswa.getFullName())
                .email(siswa.getEmail())
                .nis(siswa.getNis())
                .tempatLahir(siswa.getTempatLahir())
                .tanggalLahir(siswa.getTanggalLahir())
                .jenisKelamin(siswa.getJenisKelamin())
                .alamat(siswa.getAlamat())
                .noTeleponOrangTua(siswa.getNoTeleponOrangTua())
                .tanggalMasuk(siswa.getTanggalMasuk())
                .createdAt(siswa.getCreatedAt())
                .updatedAt(siswa.getUpdatedAt())
                .build();
    }
}
