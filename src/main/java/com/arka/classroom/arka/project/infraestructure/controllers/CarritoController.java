package com.arka.classroom.arka.project.infraestructure.controllers;

import com.arka.classroom.arka.project.Aplication.Services.CarritoService;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.infraestructure.Response.CreateCarritoProductoResponse;
import com.arka.classroom.arka.project.infraestructure.Response.OnlyCarritoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    /**
     * Servicio que me trae todos los carritos
     * @return objetos de carrito
     */
    @GetMapping("/carritos")
    public ResponseEntity<List<OnlyCarritoResponse>> getAllCarritos(){
        return new ResponseEntity<>(carritoService.getAll(), HttpStatus.OK);
    }

    /**
     * Peticion REST para crear un carritoProducto
     * @param
     * @return objeto carritoProductos
     */
    @PostMapping("/carrito")
    public ResponseEntity<List<CreateCarritoProductoResponse>> save(@RequestBody CreateCarritoDto carritoDto){
        List<CreateCarritoProductoResponse> createdCarrito = carritoService.crearCarrito(carritoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarrito);
    }

    /**
     * peticion REST para actualizar un carrito producto
     * @param carritoDto
     * @return
     */
    @PostMapping("/carrito/update")
    public ResponseEntity<CreateCarritoDto> update(@RequestBody CreateCarritoDto carritoDto){
        return new ResponseEntity<>(carritoService.updateCarrito(carritoDto), HttpStatus.OK);
    }

    /**
     * Peticion REST para consultar los carritos abandonados
     * @return retorna lista con carritos
     */
    @GetMapping("/carritos-abandonados")
    public ResponseEntity<List<Carrito>> obtenerCarritosAbandonados(){
        List<Carrito> carritoList = carritoService.obtenerCarritosAbandonados();
        return new ResponseEntity<>(carritoList, HttpStatus.OK);
    }
}
