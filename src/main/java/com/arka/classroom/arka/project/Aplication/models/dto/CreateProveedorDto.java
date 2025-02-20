package com.arka.classroom.arka.project.Aplication.models.dto;

import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateProveedorDto {

    String nombre;
    List<CreateProductoDto> productos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CreateProductoDto> getProductos() {
        return productos;
    }

    public void setProductos(List<CreateProductoDto> productos) {
        this.productos = productos;
    }
}
