package com.example.atelierapp.repositories;

import com.example.atelierapp.models.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    Optional<Collection> findByName(String name);
}
