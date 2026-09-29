package com.portofolio.LMS.service;

import com.nimbusds.jose.JOSEException;
import com.portofolio.LMS.dto.LoginRequestDTO;
import com.portofolio.LMS.dto.LoginResponseDTO;
import com.portofolio.LMS.exception.InvalidCredentialsException;
import com.portofolio.LMS.model.User;
import com.portofolio.LMS.repository.UserRepository;
import com.portofolio.LMS.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Username atau password salah"));

        if (!user.isActive()) {
            throw new InvalidCredentialsException("Akun tidak aktif");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Username atau password salah");
        }

        String token;
        try {
            token = jwtService.generateToken(user.getUsername(), user.getRole().name(), user.getId());
        } catch (JOSEException e) {
            throw new RuntimeException("Gagal membuat token", e);
        }

        return LoginResponseDTO.builder()
                .token(token)
                .tokenType("Bearer")
                .username(user.getUsername())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .build();
    }
}
