package com.example.atelierapp.dtos;

import java.util.Set;

public class ItemDTO {
    Long id;

    private String name;

    private String description;

    private Double price;

    private Long designerId;

    private Set<Long> categoryIds;

    public ItemDTO() {
    }

    public ItemDTO(String name, String description, Double price, Long designerId, Set<Long> categoryIds) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.designerId = designerId;
        this.categoryIds = categoryIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}


