package com.arka.classroom.arka.project.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.arka.classroom.arka.project.repositorys.CategoryRepository;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/")
public class CategoryProductoController {
    @Autowired
    CategoryRepository categoryRepository;

}
