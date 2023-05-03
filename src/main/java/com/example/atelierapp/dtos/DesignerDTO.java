package com.example.atelierapp.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class DesignerDTO {
    Long id;
    private String name;
    private String description;
    private Set<Long> categoryIds;
}


