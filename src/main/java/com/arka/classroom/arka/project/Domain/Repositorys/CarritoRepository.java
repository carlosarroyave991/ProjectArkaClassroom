package com.arka.classroom.arka.project.Domain.Repositorys;

import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.Domain.Entities.CarritoProducto;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    @Query("SELECT cp FROM CarritoProducto cp WHERE cp.carrito = :carrito AND cp.producto = :producto")
    Optional<CarritoProducto> findByCarritoAndProducto(@Param("carrito") Carrito carrito, @Param("producto") Producto producto);

    /*CarritoProducto agregarProductoAlCarrito(Long carritoId,Long productoId);*/

    @Query("SELECT cp FROM CarritoProducto cp WHERE cp.carrito.id = :carritoId")
    List<CarritoProducto> listarProductos(@Param("carritoId") Long carritoId);

}
