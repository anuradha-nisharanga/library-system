package com.example.api_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_service.model.dto.LoginRequestDto;
import com.example.api_service.model.dto.UserRegisterRequestDto;
import com.example.api_service.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService =userService;
    }

    @GetMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto){

        return userService.login(loginRequestDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        
        return userService.registerUser(userRegisterRequestDto);
    }


}
