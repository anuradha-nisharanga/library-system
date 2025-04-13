package com.example.api_service.service;

import java.util.List;

import com.example.api_service.model.dto.BookItemDto;
import com.example.api_service.model.dto.BookPaginationDto;

public interface BookService {

    public BookPaginationDto getBookList(int page, int size);

    public List<BookItemDto> searchBooks(String author, Integer year);
    
}
