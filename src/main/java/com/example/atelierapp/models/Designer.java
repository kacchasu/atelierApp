package com.example.atelierapp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "designers")
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "designer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Collection> collections = new HashSet<>();

    @OneToMany(mappedBy = "designer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Item> items = new HashSet<>();

    public Designer() {
    }

    public Designer(String name, String description, Set<Collection> collections, Set<Item> items) {
        this.name = name;
        this.description = description;
        this.collections = collections;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Collection> getCollections() {
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
