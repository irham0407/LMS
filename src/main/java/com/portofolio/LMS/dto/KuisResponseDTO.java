package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Kuis;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KuisResponseDTO {

    private Long id;
    private Long materiId;
    private String materiJudul;
    private String judul;
    private String deskripsi;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static KuisResponseDTO fromEntity(Kuis k) {
        return KuisResponseDTO.builder()
                .id(k.getId())
                .materiId(k.getMateri().getId())
                .materiJudul(k.getMateri().getJudul())
                .judul(k.getJudul())
                .deskripsi(k.getDeskripsi())
                .createdAt(k.getCreatedAt())
                .updatedAt(k.getUpdatedAt())
                .build();
    }
}
