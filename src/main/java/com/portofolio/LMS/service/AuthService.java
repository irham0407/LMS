package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.LoginRequestDTO;
import com.portofolio.LMS.dto.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO request);

}
