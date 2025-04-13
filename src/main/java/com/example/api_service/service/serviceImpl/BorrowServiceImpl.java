package com.example.api_service.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api_service.model.Book;
import com.example.api_service.model.BorrowRecord;
import com.example.api_service.model.User;
import com.example.api_service.repository.BookRepository;
import com.example.api_service.repository.BorrowBookRepository;
import com.example.api_service.repository.UserRepository;
import com.example.api_service.service.BorrowService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BorrowServiceImpl implements BorrowService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowBookRepository borrowBookRepository;

    @Autowired
    public BorrowServiceImpl(BookRepository bookRepository, 
                             UserRepository userRepository,
                             BorrowBookRepository borrowBookRepository){
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowBookRepository = borrowBookRepository;
    }
    

    @Override
    public ResponseEntity<String> borrowBook(Long bookId, String email) {
        log.info("Borrow Book by | Id {}", bookId);
        
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("user not found");
        }

        Book book = bookRepository.findById(bookId).orElseThrow();
        if (book.getAvailableCopies() <= 0) {
            return ResponseEntity.badRequest().body("Book not available");
        }
        
        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setUser(optionalUser.get());
        record.setBorrowedAt(LocalDateTime.now());
        borrowBookRepository.save(record);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return ResponseEntity.ok("Book borrowed successfully");
    }


    @Override
    public ResponseEntity<String> returnBook(Long bookId, String email) {
        
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("user not found");
        }

        User user = optionalUser.get();

        BorrowRecord borrowRecord = borrowBookRepository.getByUserAndBookIdAndReturnedAtNull(user.getUserId(), bookId);

        if (borrowRecord == null) {
            return ResponseEntity.badRequest().body("No active borrow record found");
        }

        BorrowRecord record = borrowRecord;
        record.setReturnedAt(LocalDateTime.now());
        borrowBookRepository.save(record);

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return ResponseEntity.ok("Book returned successfully");
    }
    
}
