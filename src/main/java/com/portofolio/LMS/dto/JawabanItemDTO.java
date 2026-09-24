package com.portofolio.LMS.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JawabanItemDTO {

    @NotNull(message = "Soal ID wajib diisi")
    private Long soalId;

    @Pattern(regexp = "^[ABCD]$", message = "Jawaban harus A, B, C, atau D")
    private String jawabanPilihan;
}
