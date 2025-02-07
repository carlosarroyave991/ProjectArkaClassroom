package com.arka.classroom.arka.project.models.dto;

import com.arka.classroom.arka.project.entities.CarritoProducto;
import com.arka.classroom.arka.project.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoDto {

    @NotBlank()
    String name;

    @NotBlank()
    String stamp;

    @NotBlank()
    Float price;

    @NotBlank()
    Integer stock;

    Categoria categoria;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
