package com.example.atelierapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "designers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(mappedBy = "designers", fetch = FetchType.LAZY)
    private Set<Category> categories = new HashSet<>();

    @PreRemove
    public void removeCategories() {
        categories.forEach(category -> category.getDesigners().remove(this));
    }

    @PrePersist
    public void addCategories() {
        categories.forEach(category -> category.getDesigners().add(this));
    }
}
