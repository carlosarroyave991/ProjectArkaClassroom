package com.arka.classroom.arka.project.Aplication.models.dto.savedcarrito;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoProductoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoRequest {
    Long id;

    Date createdDate;

    CreateClienteDto cliente;

    List<CreateCarritoProductoRequest> carritoProductos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public CreateClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(CreateClienteDto cliente) {
        this.cliente = cliente;
    }

    public List<CreateCarritoProductoRequest> getCarritoProductos() {
        return carritoProductos;
    }

    public void setCarritoProductos(List<CreateCarritoProductoRequest> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }
}
