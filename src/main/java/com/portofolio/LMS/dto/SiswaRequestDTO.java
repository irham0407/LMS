package com.portofolio.LMS.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SiswaRequestDTO {

    @NotNull(message = "User ID wajib diisi")
    private Long userId;

    private String tempatLahir;

    private LocalDate tanggalLahir;

    private String jenisKelamin;

    private String alamat;

    private String noTeleponOrangTua;

    private LocalDate tanggalMasuk;
}
