package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCategoriaDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Categoria;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    @Mapping(target = "id", ignore = true)
    Categoria toEntity(CreateCategoriaDto createCategoriaDto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "activeSince", target = "activeSince")
    CreateCategoriaDto toDto(Categoria categoria);
}
