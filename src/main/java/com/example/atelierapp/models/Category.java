package com.example.atelierapp.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Collection> collections = new HashSet<>();

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Item> items = new HashSet<>();

    @PreRemove
    public void removeFromAll() {
        collections.forEach(collection -> collection.getCategories().remove(this));
        items.forEach(item -> item.getCategories().remove(this));

    }

    // constructors, getters and setters
    public Category() {
    }

    public Category(String name, Set<Collection> collections, Set<Item> items) {
        this.name = name;
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
