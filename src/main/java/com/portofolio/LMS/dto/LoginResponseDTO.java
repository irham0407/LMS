package com.portofolio.LMS.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private String token;
    private String tokenType;
    private String username;
    private String fullName;
    private String role;
}
