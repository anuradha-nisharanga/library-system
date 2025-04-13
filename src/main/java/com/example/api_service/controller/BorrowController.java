package com.example.api_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.api_service.service.BorrowService;

@RestController
@RequestMapping("/api")
public class BorrowController {
    
    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService){
        this.borrowService = borrowService;
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId){

        return borrowService.borrowBook(bookId);
    }
}
