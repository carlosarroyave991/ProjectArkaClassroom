package com.arka.classroom.arka.project.infraestructure.Response;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class OnlyCarritoResponse {
    private Long id;

    private Date createdDate;

    private CreateClienteDto cliente;

    private List<CarritoProductoSimpleResponse> carritoProductos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CarritoProductoSimpleResponse> getCarritoProductos() {
        return carritoProductos;
    }

    public void setCarritoProductos(List<CarritoProductoSimpleResponse> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }

    public CreateClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(CreateClienteDto cliente) {
        this.cliente = cliente;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
