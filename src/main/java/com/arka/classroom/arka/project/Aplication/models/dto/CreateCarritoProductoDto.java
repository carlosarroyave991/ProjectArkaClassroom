package com.arka.classroom.arka.project.Aplication.models.dto;

import com.arka.classroom.arka.project.infraestructure.Response.OnlyProductoResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoProductoDto {

    Long id;

    Integer amount;

    @JsonIgnore
    CreateCarritoDto carrito;

    CreateProductoDto productos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public CreateCarritoDto getCarrito() {
        return carrito;
    }

    public void setCarrito(CreateCarritoDto carrito) {
        this.carrito = carrito;
    }

    public CreateProductoDto getProductos() {
        return productos;
    }

    public void setProductos(CreateProductoDto productos) {
        this.productos = productos;
    }
}
