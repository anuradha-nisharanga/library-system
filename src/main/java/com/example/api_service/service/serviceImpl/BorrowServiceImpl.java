package com.example.api_service.service.serviceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api_service.service.BorrowService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BorrowServiceImpl implements BorrowService {


    @Override
    public ResponseEntity<String> borrowBook(Long bookId) {
        log.info("Borrow Book by | Id {}", bookId);
        
        return null;
    }
    
}
