package com.portofolio.LMS.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NilaiAkhirDTO {

    private Long enrollmentId;
    private String siswaNama;
    private String siswaNis;
    private String namaKelas;
    private String mataPelajaran;
    private List<RincianKategori> rincian;
    private BigDecimal nilaiAkhir;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class RincianKategori {
        private String jenis;
        private BigDecimal rataRata;
        private Integer bobot;
        private BigDecimal kontribusi; // rataRata * bobot / 100
    }
}
