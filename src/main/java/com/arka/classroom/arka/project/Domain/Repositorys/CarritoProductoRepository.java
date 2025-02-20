package com.arka.classroom.arka.project.Domain.Repositorys;

import com.arka.classroom.arka.project.Domain.Entities.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {

}
