package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.CarritoProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarritoProductoMapper {

    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "producto", target = "producto")
    @Mapping(source = "carrito", target = "carrito")
    CarritoProducto createCarritoProductoDtoToCarritoProducto(CreateCarritoProductoDto createCarritoProductoDto);

    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "producto", target = "producto")
    @Mapping(source = "carrito", target = "carrito")
    CreateCarritoProductoDto carritoProductoToCreateCarritoProductoDto(CarritoProducto carritoProducto);
}