package com.arka.classroom.arka.project.Aplication.models.dto.savedcarrito;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCategoriaDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoRequest {
    Long id;

    String name;

    String stamp;

    BigDecimal price;

    Integer stock;

    CreateCategoriaDto categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateCategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CreateCategoriaDto categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
