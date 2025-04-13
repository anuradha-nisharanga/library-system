package com.example.api_service.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookItemDto {
    
    private String title;
    private String author;
    private int availableCopies;
}
