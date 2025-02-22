package com.arka.classroom.arka.project.infraestructure.controllers;

import com.arka.classroom.arka.project.Aplication.Services.CarritoService;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoProductoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.infraestructure.Response.CreateCarritoProductoResponse;
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
    public ResponseEntity<List<CreateCarritoDto>> getAllCarritos(){
        return new ResponseEntity<>(carritoService.getAll(), HttpStatus.OK);
    }

    /**
     * Peticion REST para crear un carritoProducto
     * @param carritoProductoDto
     * @return objeto carritoProductos
     */
    @PostMapping("/carrito")
    public ResponseEntity<CreateCarritoProductoResponse> createCarrito(@RequestBody CreateCarritoProductoDto carritoProductoDto){
        return new ResponseEntity<>(carritoService.save(carritoProductoDto), HttpStatus.CREATED);
    }
}
