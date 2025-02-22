package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.*;
import com.arka.classroom.arka.project.Domain.Entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarritoMapper {
    CarritoMapper INSTANCE = Mappers.getMapper(CarritoMapper.class);

        @Mapping(target = "cliente", source = "cliente")
        Carrito toEntity(CreateCarritoDto dto);

        @Mapping(target = "cliente", source = "cliente")
        CreateCarritoDto toDto(Carrito entity);

        @Mapping(target = "createdDate", source = "createdDate")
        @Mapping(target = "amount", source = "amount")
        @Mapping(target = "producto", source = "producto")
        CreateCarritoProductoDto carritoProductoToDto(CarritoProducto carritoProducto);

        @Mapping(target = "name", source = "name")
        @Mapping(target = "stamp", source = "stamp")
        @Mapping(target = "price", source = "price")
        @Mapping(target = "stock", source = "stock")
        CreateProductoDto productoToDto(Producto producto);

        @Mapping(target = "id", source = "id")
        @Mapping(target = "name", source = "name")
        @Mapping(target = "tipoUsuario", source = "tipoUsuario")
        @Mapping(target = "email", source = "email")
        @Mapping(target = "phone", source = "phone")
        @Mapping(target = "dni", source = "dni")
        CreateClienteDto clienteToDto(Cliente cliente);

        @Mapping(target = "id", source = "id")
        @Mapping(target = "metodoPago", source = "metodoPago")
        @Mapping(target = "reference", source = "reference")
        @Mapping(target = "date", source = "date")
        @Mapping(target = "amountValue", source = "amountValue")
        @Mapping(target = "salePrice", source = "salePrice")
        @Mapping(target = "estadoPedido", source = "estadoPedido")
        @Mapping(target = "carrito", source = "carrito")
        CreatePedidoDto pedidoToDto(Pedido pedido);

}
