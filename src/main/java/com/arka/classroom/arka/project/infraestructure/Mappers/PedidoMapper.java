package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.arka.classroom.arka.project.Domain.Entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mapping(source = "carrito", target = "carrito")
    Pedido toEntity(CreatePedidoDto createPedidoDto);

    @Mapping(source = "carrito", target = "carrito")
    CreatePedidoDto toDto(Pedido pedido);
}