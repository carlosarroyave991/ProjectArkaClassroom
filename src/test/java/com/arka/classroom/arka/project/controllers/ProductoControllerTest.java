package com.arka.classroom.arka.project.controllers;

import com.arka.classroom.arka.project.Aplication.Services.ProductoService;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.arka.classroom.arka.project.infraestructure.controllers.ProductoController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.nio.file.Paths.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductoService productoService;

    @Test
    @DisplayName("Prueba para obtener todos los productos")
    void testGetAllProductos() throws Exception {
        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setName("Producto 1");
        producto1.setStamp("Marca 1");
        producto1.setPrice(BigDecimal.valueOf(100.00));
        producto1.setStock(10);

        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setName("Producto 2");
        producto2.setStamp("Marca 2");
        producto2.setPrice(BigDecimal.valueOf(200.00));
        producto2.setStock(20);

        List<CreateProductoDto> productos = List.of(
                new CreateProductoDto(producto1.getName(), producto1.getPrice(), producto1.getStock()),
                new CreateProductoDto(producto2.getName(), producto2.getPrice(), producto2.getStock())
        );

        when(productoService.getAll()).thenReturn(productos);

        mockMvc.perform(MockMvcRequestBuilders.get("/producto"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(productos.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Producto 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(200.00));
    }

    @Test
    @DisplayName("Prueba para buscar producto por ID")
    void testFindById() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setName("cuaderno");
        producto.setPrice(BigDecimal.valueOf(150.00));
        producto.setStock(5);

        Optional<CreateProductoDto> productoDto = Optional.of(
                new CreateProductoDto(producto.getName(), producto.getPrice(), producto.getStock()));

        when(productoService.findById(1L)).thenReturn(productoDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/producto/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("cuaderno"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(150.00));
    }

    @Test
    @DisplayName("Prueba para crear un producto")
    void testSaveProducto() throws Exception {
        CreateProductoDto nuevoProducto = new CreateProductoDto("Producto Nuevo", BigDecimal.valueOf(99.99), 10);

        when(productoService.save(any(CreateProductoDto.class))).thenReturn(nuevoProducto);

        mockMvc.perform(post("/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoProducto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Producto Nuevo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(99.99));
    }

    @Test
    @DisplayName("Prueba para eliminar un producto")
    void testDeleteProducto() throws Exception {
        doNothing().when(productoService).delete(1L);

        mockMvc.perform(delete("/producto/1"))
                .andExpect(status().isOk());
    }
}
