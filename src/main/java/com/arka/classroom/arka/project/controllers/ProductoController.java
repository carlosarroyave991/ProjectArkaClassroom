package com.arka.classroom.arka.project.controllers;

import com.arka.classroom.arka.project.entities.Producto;
import com.arka.classroom.arka.project.services.ProductoService;
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
    public ResponseEntity<List<Producto>> getAllProductos() {
        return new ResponseEntity<>(productoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Producto>> findById(@PathVariable("id")Long id){
        return new ResponseEntity<>(productoService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> findByName(@RequestParam()String name){
        return new ResponseEntity<>(productoService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Producto> save(@Valid @RequestBody Producto producto){
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Producto> update(@Valid @RequestBody Producto producto){
        return new ResponseEntity<>(productoService.update(producto), HttpStatus.OK);
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
