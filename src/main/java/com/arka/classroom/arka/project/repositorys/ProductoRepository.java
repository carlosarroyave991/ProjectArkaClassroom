package com.arka.classroom.arka.project.repositorys;

import com.arka.classroom.arka.project.entities.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Override
    List<Producto> findAll(Sort sort);

    List<Producto> findByName(String name);

    List<Producto> findByPriceBetween(Float minPrice, Float maxPrice);
}
