package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.UserRequestDTO;
import com.portofolio.LMS.dto.UserResponseDTO;
import com.portofolio.LMS.exception.DuplicateResourceException;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.User;
import com.portofolio.LMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username sudah terdaftar: " + request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email sudah terdaftar: " + request.getEmail());
        }
        if (request.getNikNis() != null && userRepository.existsByNikNis(request.getNikNis())) {
            throw new DuplicateResourceException("NIK/NIS sudah terdaftar: " + request.getNikNis());
        }

        User user = User.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .nikNis(request.getNikNis())
                .active(true)
                .build();

        User saved = userRepository.save(user);
        return UserResponseDTO.fromEntity(saved);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan dengan id: " + id));
        return UserResponseDTO.fromEntity(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan dengan id: " + id));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setNikNis(request.getNikNis());

        User updated = userRepository.save(user);
        return UserResponseDTO.fromEntity(updated);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User tidak ditemukan dengan id: " + id);
        }
        userRepository.deleteById(id);
    }
}
