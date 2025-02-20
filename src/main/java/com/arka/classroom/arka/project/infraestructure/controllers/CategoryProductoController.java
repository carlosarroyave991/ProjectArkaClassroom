package com.arka.classroom.arka.project.infraestructure.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.arka.classroom.arka.project.Domain.Repositorys.CategoriaRepository;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/")
public class CategoryProductoController {
    @Autowired
    CategoriaRepository categoriaRepository;

}
