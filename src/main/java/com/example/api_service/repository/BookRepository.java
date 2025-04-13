package com.example.api_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.api_service.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    @Query(value = """
    SELECT * FROM books b 
    WHERE (:author IS NULL OR b.author ILIKE %:author%) 
      AND (:year IS NULL OR b.published_year = :year)
    """, 
    nativeQuery = true)
    List<Book> searchBookByAuthorAndYear(@Param("author") String author, @Param("year") int year);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByPublishedYear(int year);
    
    List<Book> findByAuthorAndPublishedYear(String author, int year);
    
}
