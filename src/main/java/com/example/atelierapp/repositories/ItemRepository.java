package com.example.atelierapp.repositories;

import com.example.atelierapp.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {

}
