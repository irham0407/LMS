package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.JenisNilai;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class NilaiRequestDTO {

    @NotNull(message = "Enrollment ID wajib diisi")
    private Long enrollmentId;

    @NotNull(message = "Guru ID wajib diisi")
    private Long guruId;

    @NotBlank(message = "Mata pelajaran wajib diisi")
    private String mataPelajaran;

    @NotNull(message = "Jenis wajib diisi")
    private JenisNilai jenis;

    @NotNull(message = "Nilai wajib diisi")
    @DecimalMin(value = "0", message = "Nilai minimal 0")
    @DecimalMax(value = "100", message = "Nilai maksimal 100")
    private BigDecimal nilai;

    @NotNull(message = "Tanggal wajib diisi")
    private LocalDate tanggal;

    private String keterangan;
}
