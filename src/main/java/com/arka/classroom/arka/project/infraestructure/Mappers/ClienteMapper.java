package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import com.arka.classroom.arka.project.Domain.Entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "tipoUsuario", source = "tipoUsuario")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "dni", source = "dni")
    Cliente toEntity(CreateClienteDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "tipoUsuario", source = "tipoUsuario")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "dni", source = "dni")
    CreateClienteDto toDto(Cliente entity);
}
