package com.arka.classroom.arka.project.repositorys;

import com.arka.classroom.arka.project.entities.Categoria;
import com.arka.classroom.arka.project.models.dto.CreateCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAll();

    List<Categoria> findByName(String name);
}
