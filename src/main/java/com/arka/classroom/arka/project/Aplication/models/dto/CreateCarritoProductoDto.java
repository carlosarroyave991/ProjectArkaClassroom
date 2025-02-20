package com.arka.classroom.arka.project.Aplication.models.dto;

import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoProductoDto {

    Long id;

    Date createdDate;

    Integer amount;

    CreateCarritoDto carrito;

    CreateProductoDto producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public CreateProductoDto getProducto() {
        return producto;
    }

    public void setProducto(CreateProductoDto producto) {
        this.producto = producto;
    }
}
