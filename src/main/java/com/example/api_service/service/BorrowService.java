package com.example.api_service.service;

import org.springframework.http.ResponseEntity;

public interface BorrowService {

    ResponseEntity<String> borrowBook(Long bookId);
    
}
