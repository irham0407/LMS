package com.portofolio.LMS.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KuisRequestDTO {

    @NotNull(message = "Materi ID wajib diisi")
    private Long materiId;

    @NotBlank(message = "Judul wajib diisi")
    private String judul;

    private String deskripsi;
}
