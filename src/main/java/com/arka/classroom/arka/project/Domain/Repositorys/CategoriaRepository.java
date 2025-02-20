package com.arka.classroom.arka.project.Domain.Repositorys;

import com.arka.classroom.arka.project.Domain.Entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByName(String name);

    Optional<Categoria> findOneByName(String name);

}