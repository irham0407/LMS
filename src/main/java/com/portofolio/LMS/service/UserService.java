package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.UserRequestDTO;
import com.portofolio.LMS.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long id, UserRequestDTO request);

    void deleteUser(Long id);
}
