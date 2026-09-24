package com.portofolio.LMS.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KelasRequestDTO {

    @NotBlank(message = "Nama kelas wajib diisi")
    private String namaKelas;

    @NotBlank(message = "Tingkat wajib diisi")
    private String tingkat;

    private Long waliKelasId; // opsional, id dari Guru

    @NotBlank(message = "Tahun ajaran wajib diisi")
    private String tahunAjaran;
}
