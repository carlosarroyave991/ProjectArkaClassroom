package com.arka.classroom.arka.project.Aplication.models.dto;

import com.arka.classroom.arka.project.Domain.Entities.CarritoProducto;
import com.arka.classroom.arka.project.Domain.Entities.Pedido;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoDto {

    Long id;

    CreateClienteDto cliente;

    List<CreateCarritoProductoDto> carritoProductos;

    Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(CreateClienteDto cliente) {
        this.cliente = cliente;
    }

    public List<CreateCarritoProductoDto> getCarritoProductos() {
        return carritoProductos;
    }

    public void setCarritoProductos(List<CreateCarritoProductoDto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
