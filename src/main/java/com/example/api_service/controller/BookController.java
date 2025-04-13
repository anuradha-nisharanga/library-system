package com.example.api_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_service.model.dto.BookItemDto;
import com.example.api_service.model.dto.BookPaginationDto;
import com.example.api_service.service.BookService;

@RestController
@RequestMapping(path = "/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService =bookService;
    }

    @GetMapping
    public BookPaginationDto viewBookList(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size){
        return bookService.getBookList(page, size);

    }

    @GetMapping("/search")
    public List<BookItemDto> searchBooks(@RequestParam(value = "author", required = false) String author,
                                        @RequestParam(value = "year", required = false) Integer year){

        return bookService.searchBooks(author, year);
    }
    
    
}
