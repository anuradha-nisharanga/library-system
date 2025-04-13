package com.example.api_service.service;

import org.springframework.http.ResponseEntity;

public interface BorrowService {

    ResponseEntity<String> borrowBook(Long bookId, String authId);

    ResponseEntity<String> returnBook(Long bookId, String authId);
    
}
