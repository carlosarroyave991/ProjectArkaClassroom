package com.arka.classroom.arka.project.infraestructure.controllers;

import com.arka.classroom.arka.project.Aplication.Services.CarritoService;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<CreateCarritoDto> getAllCarritos(){
        return carritoService.getAll();
    }
}
