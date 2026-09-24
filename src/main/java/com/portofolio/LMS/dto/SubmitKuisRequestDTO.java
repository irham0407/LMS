package com.portofolio.LMS.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubmitKuisRequestDTO {

    @NotNull(message = "Kuis ID wajib diisi")
    private Long kuisId;

    @NotNull(message = "Siswa ID wajib diisi")
    private Long siswaId;

    @NotEmpty(message = "Jawaban tidak boleh kosong")
    @Valid
    private List<JawabanItemDTO> jawaban;
}
