package com.example.api_service.model.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    
    private String email;
    private String password;
    private String username;

}
