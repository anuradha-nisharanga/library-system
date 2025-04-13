package com.example.api_service.service;

import org.springframework.http.ResponseEntity;

import com.example.api_service.model.dto.LoginRequestDto;
import com.example.api_service.model.dto.UserRegisterRequestDto;

public interface UserService {

    String login(LoginRequestDto loginRequestDto);

    ResponseEntity<String> registerUser(UserRegisterRequestDto userRegisterRequestDto);

    
}
