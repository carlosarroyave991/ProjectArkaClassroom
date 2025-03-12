package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.*;
import com.arka.classroom.arka.project.Domain.Entities.*;
import com.arka.classroom.arka.project.infraestructure.Response.OnlyCarritoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarritoMapper {
    //CarritoMapper INSTANCE = Mappers.getMapper(CarritoMapper.class);

        @Mapping(target = "pedido", ignore = true)
        Carrito toEntity(CreateCarritoDto dto);
        CreateCarritoDto toDto(Carrito entity);

        CarritoProducto toEntity(CreateCarritoProductoDto carritoProductoDto);
        CreateCarritoProductoDto toDto(CarritoProducto carritoProducto);

        Producto toEntity(CreateProductoDto createProductoDto);
        CreateProductoDto toDto(Producto producto);

        List<OnlyCarritoResponse> carritoToResponse(List<Carrito> carrito);
}
