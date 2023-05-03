package com.example.atelierapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_designer", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "designer_id"))
    private Set<Designer> designers = new HashSet<>();

    @PreRemove
    public void removeFromAll() {
        collections.forEach(collection -> collection.getCategories().remove(this));
        items.forEach(item -> item.getCategories().remove(this));

    }
}
