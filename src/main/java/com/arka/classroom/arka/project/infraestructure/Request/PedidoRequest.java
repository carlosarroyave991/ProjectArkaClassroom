package com.arka.classroom.arka.project.infraestructure.Request;

import com.arka.classroom.arka.project.Domain.Entities.enums.EstadoPedido;
import com.arka.classroom.arka.project.infraestructure.Response.OnlyCarritoResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    Long id;

    String metodoPago;

    Long reference;

    Date date;

    BigDecimal amountValue;

    BigDecimal salePrice;

    EstadoPedido estadoPedido;

    Long carritoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(BigDecimal amountValue) {
        this.amountValue = amountValue;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        if (estadoPedido != null) {
            try {
                this.estadoPedido = EstadoPedido.valueOf(estadoPedido.toString().toLowerCase());
            } catch (IllegalArgumentException e) {
                this.estadoPedido = EstadoPedido.pendiente;
            }
        } else {
            this.estadoPedido = EstadoPedido.pendiente; // Valor predeterminado si es null
        }
    }
}
