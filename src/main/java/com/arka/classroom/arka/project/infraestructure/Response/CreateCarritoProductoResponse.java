package com.arka.classroom.arka.project.infraestructure.Response;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoProductoResponse {

    private Long id;

    private Date createdDate;

    private Integer amount;

    private CreateCarritoDto carrito;

    private OnlyProductoResponse producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateCarritoDto getCarrito() {
        return carrito;
    }

    public void setCarrito(CreateCarritoDto carrito) {
        this.carrito = carrito;
    }

    public OnlyProductoResponse getProducto() {
        return producto;
    }

    public void setProducto(OnlyProductoResponse producto) {
        this.producto = producto;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
