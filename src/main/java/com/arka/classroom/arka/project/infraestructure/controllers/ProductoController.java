package com.arka.classroom.arka.project.infraestructure.controllers;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Aplication.Services.ProductoService;
import com.arka.classroom.arka.project.infraestructure.Response.OnlyProductoResponse;
import com.arka.classroom.arka.project.infraestructure.Response.ProductosByCategoriaResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<CreateProductoDto>> getAllProductos() {
        return new ResponseEntity<>(productoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CreateProductoDto>> findById(@PathVariable("id")Long id){
        return new ResponseEntity<>(productoService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CreateProductoDto>> findByName(@RequestParam()String name){
        return new ResponseEntity<>(productoService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/categoria/{id}/productos")
    public ResponseEntity<ProductosByCategoriaResponse> getProductosByCategoria(@PathVariable("id")Long id) {
        Optional<ProductosByCategoriaResponse> response = productoService.getProductosByCategoria(id);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<CreateProductoDto> save(@RequestBody CreateProductoDto productoDto){
        return new ResponseEntity<>(productoService.save(productoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateProductoDto> update(@PathVariable("id")Long id, @RequestBody CreateProductoDto productoDto){
        return new ResponseEntity<>(productoService.update(id, productoDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        productoService.delete(id);
        return new ResponseEntity<>("Se eliminó correctamente", HttpStatus.OK);
    }

    @GetMapping("/buscar-precios")
    public ResponseEntity<List<Producto>> getProductosPorPrecio(
            @RequestParam Float minPrice,
            @RequestParam Float maxPrice
    ){
        return new ResponseEntity<>(productoService.getProductosPorRangoDePrecio(minPrice, maxPrice), HttpStatus.OK);
    }
}
