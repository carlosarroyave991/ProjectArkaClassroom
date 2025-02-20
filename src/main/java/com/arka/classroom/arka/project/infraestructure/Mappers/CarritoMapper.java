package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoProductoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.Domain.Entities.CarritoProducto;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarritoMapper {
    CarritoMapper INSTANCE = Mappers.getMapper(CarritoMapper.class);

        @Mapping(target = "cliente", source = "cliente")
        @Mapping(target = "carritoProductos", source = "carritoProductos")
        @Mapping(target = "pedido", source = "pedido")
        Carrito toEntity(CreateCarritoDto dto);

        @Mapping(target = "cliente", source = "cliente")
        @Mapping(target = "carritoProductos", source = "carritoProductos")
        @Mapping(target = "pedido", source = "pedido")
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

}
