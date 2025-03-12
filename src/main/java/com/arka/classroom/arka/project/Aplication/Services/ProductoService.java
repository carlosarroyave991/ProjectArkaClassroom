package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCategoriaDto;
import com.arka.classroom.arka.project.Domain.Entities.Categoria;
import com.arka.classroom.arka.project.Domain.Entities.Pedido;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Repositorys.*;
import com.arka.classroom.arka.project.infraestructure.Mappers.CategoriaMapper;
import com.arka.classroom.arka.project.infraestructure.Mappers.ProductoMapper;
import com.arka.classroom.arka.project.infraestructure.Response.OnlyProductoResponse;
import com.arka.classroom.arka.project.infraestructure.Response.ProductosByCategoriaResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductoService {
    private final static String PRODUCT_YA_EXISTE = "El producto ya existe en la base de datos";
    private final static String PRODUCT_NO_ENCONTRADO = "El producto no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProductoMapper productoMapper;

    @Autowired
    CategoriaMapper categoriaMapper;

    /**
     * Funcion que consulta todos los productos con sus categorias
     * @return una lista de objetos mapeados de entidad a Dto
     */
    public List<CreateProductoDto> getAll() {
        //return productoRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Funcion que consulta los productos que se llamen igual
     * @param name del objeto a buscar
     * @return returna una lista de objetos mapeados en el dto
     */
    public List<CreateProductoDto> findByName(String name) {
        List<Producto> productos = productoRepository.findByName(name);
        if (productos.isEmpty()) {
            throw new GeneralException(PRODUCT_NO_ENCONTRADO);
        } else {
            return productos.stream()
                    .map(productoMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Funcion que consulta un producto por medio del id
     * @param id clave del objeto a buscar
     * @return retorna el dto mapeado desde la entidad
     */
    public Optional<CreateProductoDto> findById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            CreateProductoDto productoDto = productoMapper.toDto(producto.get());
            return Optional.of(productoDto);
        } else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    /**
     * Servicio que me consultara todos los productos que pertenezcan a una misma categoria
     * @param id de la categoria
     * @return El objeto de respuesta ordenado con la categoria y los productos
     */
    public Optional<ProductosByCategoriaResponse> getProductosByCategoria(Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isEmpty()){
            throw new GeneralException(ID_NO_ENCONTRADO);
        }

        Categoria categoria = categoriaOptional.get();
        List<Producto> productos = productoRepository.findByCategoria(categoria);

        //Convierto la lista de productos a el response que necesito
        List<OnlyProductoResponse> productoResponses = productos.stream()
                .map(productoMapper::productoToOnlyProductoResponse)
                .collect(Collectors.toList());

        ProductosByCategoriaResponse response = productoMapper.categoriaAndProductosToProductosByCategoriaResponse(categoria, productoResponses);

        return Optional.of(response);
    }

    /**
     * Permite crear un producto con categorias
     * @param productoDto el objeto del Dto
     * @return el objeto del Dto mapeado de entidad a Dto
     */
    public CreateProductoDto save(CreateProductoDto productoDto) {
        List<Producto> productoList = productoRepository.findByName(productoDto.getName());
        if (!productoList.isEmpty()) {
            throw new GeneralException(PRODUCT_YA_EXISTE);
        }

        Categoria categorias = getOrCreateCategoria(productoDto.getCategoria());

        // Utiliza el mapper para convertir el DTO a la entidad Producto
        Producto producto = productoMapper.toEntity(productoDto);
        producto.setCategoria(categorias); // Asignar categorías
        Producto savedProducto = productoRepository.save(producto);

        //Utiliza el mapper para convertir la entidad guardada de vuelta al DTO
        return productoMapper.toDto(savedProducto);
    }

    /**
     * Método para obtener o crear categorías
     */
    private Categoria getOrCreateCategoria (CreateCategoriaDto categoriaDto){
        List<Categoria> categoriaList = categoriaRepository.findByName(categoriaDto.getName());
        if (categoriaList.isEmpty()) {
            Categoria newCategoria = categoriaMapper.toEntity(categoriaDto);
            newCategoria.setActiveSince(new Date());
            return categoriaRepository.save(newCategoria);
        } else {
            return categoriaList.get(0);
        }
    }

    /**
     * Utiliza el metodo productoDto del productoMapper para convertir
     * el objeto createProductoDto en un objeto producto
     * @param producto recibe un objeto tipo producto
     * @return devuelve un objeto tipo CreateProductoDto
     */
    public CreateProductoDto convertToDto(Producto producto){
        return productoMapper.toDto(producto);
    }

    /**
     * Utiliza el método createProductoDtoToProducto del ProductoMapper
     * para convertir el objeto CreateProductoDto en un objeto Producto
     * @param createProductoDto recibe un objeto tipo createProcutoDto
     * @return Devuelve un objeto tipo Producto
     */
    public Producto convertToEntity(CreateProductoDto createProductoDto){
        return productoMapper.toEntity(createProductoDto);
    }


    public CreateProductoDto update(Long id, CreateProductoDto productoDto) {
        Producto existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ID_NO_ENCONTRADO));

        Producto updatedProducto = productoMapper.toEntity(productoDto);

        // Mantener el ID del producto existente
        updatedProducto.setId(existingProducto.getId());

        // Mantener los datos existentes si no se proporcionan nuevos
        updatedProducto.setName(Optional.ofNullable(productoDto.getName()).orElse(existingProducto.getName()));
        updatedProducto.setStamp(Optional.ofNullable(productoDto.getStamp()).orElse(existingProducto.getStamp()));
        updatedProducto.setPrice(Optional.ofNullable(productoDto.getPrice()).orElse(existingProducto.getPrice()));
        updatedProducto.setStock(Optional.ofNullable(productoDto.getStock()).orElse(existingProducto.getStock()));

        if (productoDto.getCategoria() != null) {
            Categoria categoria = categoriaRepository.findOneByName(productoDto.getCategoria().getName())
                    .orElseThrow(() -> new GeneralException("Categoria no encontrada"));
            updatedProducto.setCategoria(categoria);
        } else {
            updatedProducto.setCategoria(existingProducto.getCategoria());
        }

        Producto savedProducto = productoRepository.save(updatedProducto);
        return productoMapper.toDto(savedProducto);
    }


    public void delete (Long id){
        Optional<Producto> result = productoRepository.findById(id);
        if (result.isPresent()) {
            productoRepository.deleteById(id);
        } else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public List<Producto> getProductosPorRangoDePrecio (Float minPrice, Float maxPrice){
        return productoRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
