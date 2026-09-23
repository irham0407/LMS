package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private User.Role role;
    private String nikNis;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static UserResponseDTO fromEntity(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .nikNis(user.getNikNis())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
