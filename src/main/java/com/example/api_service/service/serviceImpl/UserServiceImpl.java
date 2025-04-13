package com.example.api_service.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api_service.model.User;
import com.example.api_service.model.dto.LoginRequestDto;
import com.example.api_service.model.dto.UserRegisterRequestDto;
import com.example.api_service.repository.UserRepository;
import com.example.api_service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return "login successfully";
    }

    @Override
    public ResponseEntity<String> registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        
        if (userRepository.findByEmail(userRegisterRequestDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        User user = User.builder()
        .email(userRegisterRequestDto.getEmail())
        .name(userRegisterRequestDto.getUsername())
        .password(passwordEncoder.encode(userRegisterRequestDto.getPassword()))
        .build();
    
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    
}
