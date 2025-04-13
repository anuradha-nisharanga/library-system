package com.example.api_service.repository;

import java.nio.file.OpenOption;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.api_service.model.BorrowRecord;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowRecord, Long> {
 
    @Query(value = "SELECT distinct bb FROM borrow_record bb WHERE bb.user = (:userId) and bb.book = (:bookId) and returned_at IS NULL", nativeQuery = true)
    BorrowRecord getByUserAndBookIdAndReturnedAtNull(@Param("userId") Long userId, @Param("bookId") Long bookId);
    
}
