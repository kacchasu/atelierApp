package com.example.atelierapp.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class ItemDTO {
    Long id;
    private String name;
    private String description;
    private Double price;
    private Long designerId;
    private Set<Long> categoryIds;
}


