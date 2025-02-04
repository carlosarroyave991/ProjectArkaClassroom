package com.arka.classroom.arka.project.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.arka.classroom.arka.project.entities.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
    Page<Marca> findAll(Pageable pagination);

}
