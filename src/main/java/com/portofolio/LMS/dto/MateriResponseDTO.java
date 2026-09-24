package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.Materi;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MateriResponseDTO {

    private Long id;
    private Long guruId;
    private String guruNama;
    private String mataPelajaran;
    private String judul;
    private String deskripsi;
    private String fileUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MateriResponseDTO fromEntity(Materi m) {
        return MateriResponseDTO.builder()
                .id(m.getId())
                .guruId(m.getGuru().getId())
                .guruNama(m.getGuru().getFullName())
                .mataPelajaran(m.getMataPelajaran())
                .judul(m.getJudul())
                .deskripsi(m.getDeskripsi())
                .fileUrl(m.getFileUrl())
                .createdAt(m.getCreatedAt())
                .updatedAt(m.getUpdatedAt())
                .build();
    }
}
