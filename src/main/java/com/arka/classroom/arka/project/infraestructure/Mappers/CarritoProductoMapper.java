package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoProductoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.Domain.Entities.CarritoProducto;
import com.arka.classroom.arka.project.Domain.Entities.Embed.CarritoProductoId;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.arka.classroom.arka.project.infraestructure.Response.CarritoProductoSimpleResponse;
import com.arka.classroom.arka.project.infraestructure.Response.CreateCarritoProductoResponse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

//al usar el @Mapper(componentModel= "spring") ya no es necesaria instanciarse de nuevo
@Mapper(componentModel = "spring")
public interface CarritoProductoMapper {

    /*CarritoProductoMapper INSTANCE = Mappers.getMapper(CarritoProductoMapper.class);*/

    CarritoProducto toEntity(CreateCarritoProductoDto createCarritoProductoDto);
    @Mapping(target = "carrito", ignore = true)
    CreateCarritoProductoDto toDto(CarritoProducto carritoProducto);

    List<CreateCarritoProductoResponse> carritoProductoToCreateCarritoProductoResponse(List<CarritoProducto> carritoProducto);

    List<CarritoProductoSimpleResponse> ToSimpleReponse(List<CarritoProducto> carritoProducto);
}
