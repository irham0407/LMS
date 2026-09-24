package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.SoalKuis;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoalKuisResponseDTO {

    private Long id;
    private Long kuisId;
    private String pertanyaan;
    private String pilihanA;
    private String pilihanB;
    private String pilihanC;
    private String pilihanD;
    private String jawabanBenar;
    private Integer skor;

    public static SoalKuisResponseDTO fromEntity(SoalKuis s) {
        return SoalKuisResponseDTO.builder()
                .id(s.getId())
                .kuisId(s.getKuis().getId())
                .pertanyaan(s.getPertanyaan())
                .pilihanA(s.getPilihanA())
                .pilihanB(s.getPilihanB())
                .pilihanC(s.getPilihanC())
                .pilihanD(s.getPilihanD())
                .jawabanBenar(s.getJawabanBenar())
                .skor(s.getSkor())
                .build();
    }

    // Versi tanpa jawaban benar - dipakai saat siswa MENGERJAKAN kuis (tidak boleh lihat kunci jawaban)
    public static SoalKuisResponseDTO fromEntityHideAnswer(SoalKuis s) {
        SoalKuisResponseDTO dto = fromEntity(s);
        dto.setJawabanBenar(null);
        return dto;
    }
}
