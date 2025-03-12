package com.arka.classroom.arka.project.infraestructure.Response;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoProductoResponse {

    private Long id;

    private Integer amount;

    @JsonIgnore
    private OnlyCarritoResponse carrito;

    private OnlyProductoResponse producto;

    private CreatePedidoDto pedido;


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

    public OnlyCarritoResponse getCarrito() {
        return carrito;
    }

    public void setCarrito(OnlyCarritoResponse carrito) {
        this.carrito = carrito;
    }

    public OnlyProductoResponse getProducto() {
        return producto;
    }

    public void setProducto(OnlyProductoResponse producto) {
        this.producto = producto;
    }

    public CreatePedidoDto getPedido() {
        return pedido;
    }

    public void setPedido(CreatePedidoDto pedido) {
        this.pedido = pedido;
    }
}
