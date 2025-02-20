package com.arka.classroom.arka.project.infraestructure.Mappers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Categoria;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.arka.classroom.arka.project.infraestructure.Response.OnlyProductoResponse;
import com.arka.classroom.arka.project.infraestructure.Response.ProductosByCategoriaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(source = "categoria", target = "categoria")
    CreateProductoDto productoToCreateProductoDto(Producto producto);

    @Mapping(source = "categoria", target = "categoria")
    Producto createProductoDtoToProducto(CreateProductoDto createProductoDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "stamp", target = "stamp")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    OnlyProductoResponse productoToOnlyProductoResponse(Producto producto);


    @Mapping(source = "productos", target = "productosResponseList")
    ProductosByCategoriaResponse categoriaAndProductosToProductosByCategoriaResponse(Categoria categoria, List<OnlyProductoResponse> productos);
}

