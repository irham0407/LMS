package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Absensi;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AbsensiRequestDTO {

    @NotNull(message = "Enrollment ID wajib diisi")
    private Long enrollmentId;

    @NotNull(message = "Guru ID wajib diisi")
    private Long guruId;

    @NotNull(message = "Tanggal wajib diisi")
    private LocalDate tanggal;

    @NotNull(message = "Status wajib diisi")
    private Absensi.Status status;

    private String keterangan;
}
