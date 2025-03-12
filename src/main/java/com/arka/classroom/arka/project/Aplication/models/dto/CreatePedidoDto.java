package com.arka.classroom.arka.project.Aplication.models.dto;
import com.arka.classroom.arka.project.Domain.Entities.enums.EstadoPedido;
import com.arka.classroom.arka.project.infraestructure.Response.OnlyCarritoResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
public class CreatePedidoDto {

    Long id;

    String metodoPago;

    Long reference;

    Date date;

    BigDecimal amountValue;

    BigDecimal salePrice;

    EstadoPedido estadoPedido;

    /*CreateClienteDto cliente;*/

    OnlyCarritoResponse carrito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(BigDecimal amountValue) {
        this.amountValue = amountValue;
    }

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

    public void setEstadoPedido(String estadoPedido) {
        if (estadoPedido != null) {
            try {
                this.estadoPedido = EstadoPedido.valueOf(estadoPedido.toLowerCase());
            } catch (IllegalArgumentException e) {
                this.estadoPedido = EstadoPedido.pendiente;
            }
        } else {
            this.estadoPedido = EstadoPedido.pendiente; // Valor predeterminado si es null
        }
    }

    public OnlyCarritoResponse getCarrito() {
        return carrito;
    }

    public void setCarrito(OnlyCarritoResponse carrito) {
        this.carrito = carrito;
    }

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }
}
