package com.example.api_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_service.model.BorrowRecord;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowRecord, Long> {
    
}
