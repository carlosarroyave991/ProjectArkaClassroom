package com.arka.classroom.arka.project.repositorys;

import com.arka.classroom.arka.project.entities.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAll(Sort sort);

    List<Producto> findByName(String name);

    List<Producto> findByPriceBetween(Float minPrice, Float maxPrice);
}
