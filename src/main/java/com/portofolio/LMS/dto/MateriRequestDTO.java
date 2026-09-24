package com.portofolio.LMS.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriRequestDTO {

    @NotNull(message = "Guru ID wajib diisi")
    private Long guruId;

    @NotBlank(message = "Mata pelajaran wajib diisi")
    private String mataPelajaran;

    @NotBlank(message = "Judul wajib diisi")
    private String judul;

    private String deskripsi;

    private String fileUrl;
}
