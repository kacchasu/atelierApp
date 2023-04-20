package com.example.atelierapp.repositories;

import com.example.atelierapp.models.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {

}
