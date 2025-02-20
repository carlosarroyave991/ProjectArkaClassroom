package com.arka.classroom.arka.project.Aplication.models.dto;

import com.arka.classroom.arka.project.Domain.Entities.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
public class CreatePedidoDto {

    String metodoPago;

    Long reference;

    Date date;

    BigDecimal salePrice;

    EstadoPedido estadoPedido;

    /*CreateClienteDto cliente;*/

    CreateCarritoDto carrito;

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public void setCarrito(CreateCarritoDto carrito) {
        this.carrito = carrito;
    }

    public CreateCarritoDto getCarrito() {
        return carrito;
    }

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }
}
