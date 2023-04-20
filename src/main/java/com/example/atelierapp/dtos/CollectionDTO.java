package com.example.atelierapp.dtos;

import java.util.Set;

public class CollectionDTO {
    Long id;
    private String name;
    private String description;
    private Long designerId;
    private Set<Long> itemIds;
    private Set<Long> categoryIds;

    public CollectionDTO() {
    }

    public CollectionDTO(String name, String description, Long designerId, Set<Long> itemIds, Set<Long> categoryIds) {
        this.name = name;
        this.description = description;
        this.designerId = designerId;
        this.itemIds = itemIds;
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

    public Long getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    public Set<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(Set<Long> itemIds) {
        this.itemIds = itemIds;
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


