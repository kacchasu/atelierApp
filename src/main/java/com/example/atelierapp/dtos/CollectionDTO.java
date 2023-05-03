package com.example.atelierapp.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class CollectionDTO {
    Long id;
    private String name;
    private String description;
    private Long designerId;
    private Set<Long> itemIds;
    private Set<Long> categoryIds;

}


