package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.JenisNilai;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BobotPenilaianRequestDTO {

    @NotBlank(message = "Mata pelajaran wajib diisi")
    private String mataPelajaran;

    @NotNull(message = "Jenis wajib diisi")
    private JenisNilai jenis;

    @NotNull(message = "Bobot wajib diisi")
    @Min(value = 0, message = "Bobot minimal 0")
    @Max(value = 100, message = "Bobot maksimal 100")
    private Integer bobot;
}
