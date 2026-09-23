package com.portofolio.LMS.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GuruRequestDTO {

    @NotNull(message = "User ID wajib diisi")
    private Long userId;

    @NotBlank(message = "Mata pelajaran wajib diisi")
    private String mataPelajaran;

    private String spesialisasi;

    private String noTelepon;

    private String alamat;

    private LocalDate tanggalBergabung;
}
