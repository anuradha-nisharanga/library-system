package com.example.api_service.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.api_service.model.Book;
import com.example.api_service.model.dto.BookItemDto;
import com.example.api_service.model.dto.BookPaginationDto;
import com.example.api_service.repository.BookRepository;
import com.example.api_service.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookPaginationDto getBookList(int page, int size) {
        log.info("View Book List | page {} size {}", page, size);
        
        PageRequest pageRequest = PageRequest.of(page, size);
        
        Page<Book> bookPage = bookRepository.findAll(pageRequest);

        List<BookItemDto> bookItems = bookPage.stream().map(this::bookConvertToBookItem).toList();

        return BookPaginationDto.builder()
        .items(bookItems)
        .currentPage(bookPage.getNumber())
        .totalItems(bookPage.getTotalElements())
        .totalPages(bookPage.getTotalPages())
        .build();
    }
    
    @Override
    public List<BookItemDto> searchBooks(String author, Integer year) {
        log.info("Search by | author {} year {}", author, year);

        // bookRepository.searchBookByAuthorAndYear(author, year);
        List<Book> bookList = new ArrayList<>(); 

        if (author != null && year != null) {
            bookList = bookRepository.findByAuthorAndPublishedYear(author, year);
        } else if (author != null) {
            bookList = bookRepository.findByAuthorContainingIgnoreCase(author);
        } else if (year != null) {
            bookList =  bookRepository.findByPublishedYear(year);
        } else {
            bookList = bookRepository.findAll();
        }
        
        return bookList.stream().map(this::bookConvertToBookItem).toList();
    }


    private BookItemDto bookConvertToBookItem(Book book){

        return BookItemDto.builder()
        .title(book.getTitle())
        .author(book.getAuthor())
        .availableCopies(book.getAvailableCopies())
        .build();
    }

    
}
