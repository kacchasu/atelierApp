package com.example.atelierapp.dtos;

public class DesignerDTO {
    Long id;

    private String name;

    private String description;

    public DesignerDTO() {
    }

    public DesignerDTO(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


