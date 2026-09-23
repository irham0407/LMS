package com.portofolio.LMS.dto;

import com.portofolio.LMS.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message = "Username wajib diisi")
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,50}$", message = "Username hanya boleh huruf, angka, titik, underscore, dan strip (3-50 karakter)")
    private String username;

    @NotBlank(message = "Nama lengkap wajib diisi")
    private String fullName;

    @NotBlank(message = "Email wajib diisi")
    @Email(message = "Format email tidak valid, harus mengandung '@' dan domain yang sesuai")
    private String email;

    @NotBlank(message = "Password wajib diisi")
    private String password;

    @NotNull(message = "Role wajib diisi")
    private User.Role role;

    private String nikNis;
}
