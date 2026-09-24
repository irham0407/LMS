package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.HasilKuis;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HasilKuisResponseDTO {

    private Long id;
    private Long kuisId;
    private String kuisJudul;
    private Long siswaId;
    private String siswaNama;
    private Integer skorTotal;
    private LocalDateTime tanggalPengerjaan;

    public static HasilKuisResponseDTO fromEntity(HasilKuis h) {
        return HasilKuisResponseDTO.builder()
                .id(h.getId())
                .kuisId(h.getKuis().getId())
                .kuisJudul(h.getKuis().getJudul())
                .siswaId(h.getSiswa().getId())
                .siswaNama(h.getSiswa().getFullName())
                .skorTotal(h.getSkorTotal())
                .tanggalPengerjaan(h.getTanggalPengerjaan())
                .build();
    }
}
