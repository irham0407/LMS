package com.portofolio.LMS.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EnrollmentRequestDTO {

    @NotNull(message = "Siswa ID wajib diisi")
    private Long siswaId;

    @NotNull(message = "Kelas ID wajib diisi")
    private Long kelasId;

    @NotNull(message = "Tahun ajaran wajib diisi")
    private String tahunAjaran;

    @NotNull(message = "Tanggal masuk wajib diisi")
    private LocalDate tanggalMasuk;
}
