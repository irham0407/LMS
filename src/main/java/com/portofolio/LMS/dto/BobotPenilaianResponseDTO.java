package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.BobotPenilaian;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BobotPenilaianResponseDTO {
    private Long id;
    private String mataPelajaran;
    private String jenis;
    private Integer bobot;

    public static BobotPenilaianResponseDTO fromEntity(BobotPenilaian b) {
        return BobotPenilaianResponseDTO.builder()
                .id(b.getId())
                .mataPelajaran(b.getMataPelajaran())
                .jenis(b.getJenis().name())
                .bobot(b.getBobot())
                .build();
    }
}
