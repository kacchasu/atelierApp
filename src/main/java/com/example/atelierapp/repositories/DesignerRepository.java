package com.example.atelierapp.repositories;

import com.example.atelierapp.models.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource
public interface DesignerRepository extends JpaRepository<Designer, Long> {
    List<Designer> findByCategoriesIdIn(@RequestParam("categoryId") List<Long> categoryId);
}
