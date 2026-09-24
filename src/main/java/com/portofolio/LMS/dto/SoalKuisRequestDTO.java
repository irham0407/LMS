package com.portofolio.LMS.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoalKuisRequestDTO {

    @NotNull(message = "Kuis ID wajib diisi")
    private Long kuisId;

    @NotBlank(message = "Pertanyaan wajib diisi")
    private String pertanyaan;

    @NotBlank(message = "Pilihan A wajib diisi")
    private String pilihanA;

    @NotBlank(message = "Pilihan B wajib diisi")
    private String pilihanB;

    @NotBlank(message = "Pilihan C wajib diisi")
    private String pilihanC;

    @NotBlank(message = "Pilihan D wajib diisi")
    private String pilihanD;

    @Pattern(regexp = "^[ABCD]$", message = "Jawaban benar harus A, B, C, atau D")
    private String jawabanBenar;

    private Integer skor;
}
