package com.arka.classroom.arka.project.Domain.Repositorys;

import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Categoria;
import com.arka.classroom.arka.project.Domain.Entities.Pedido;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAll(Sort sort);

    List<Producto> findByName(String name);

    List<Producto> findByPriceBetween(Float minPrice, Float maxPrice);

    List<Producto> findByCategoria(Categoria categoria);
}

