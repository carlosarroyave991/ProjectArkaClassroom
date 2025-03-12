package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.arka.classroom.arka.project.Domain.Entities.Embed.CarritoProductoId;
import com.arka.classroom.arka.project.Domain.Entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    /*PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);*/

    @Mapping(target = "metodoPago", source = "metodoPago")
    @Mapping(target = "carrito.id", source = "carrito.id")
    Pedido toEntity(CreatePedidoDto createPedidoDto);

    @Mapping(target = "metodoPago", source = "metodoPago")
    @Mapping(target = "carrito.id", source = "carrito.id")
    CreatePedidoDto toDto(Pedido pedido);

    List<CreatePedidoDto> toListDto(List<Pedido> pedidos);

}