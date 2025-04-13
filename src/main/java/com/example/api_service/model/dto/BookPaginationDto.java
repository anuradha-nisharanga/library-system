package com.example.api_service.model.dto;

import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookPaginationDto {

    private long totalItems;
    private int totalPages;
    private int currentPage;
    private List<BookItemDto> items;
    
}
