package com.arka.classroom.arka.project.infraestructure.Response;

import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CarritoProductoSimpleResponse {
    private Long id;

    private Integer amount;

    private OnlyProductoResponse producto;

    private CreatePedidoDto pedido;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreatePedidoDto getPedido() {
        return pedido;
    }

    public void setPedido(CreatePedidoDto pedido) {
        this.pedido = pedido;
    }

    public OnlyProductoResponse getProducto() {
        return producto;
    }

    public void setProducto(OnlyProductoResponse producto) {
        this.producto = producto;
    }
}
