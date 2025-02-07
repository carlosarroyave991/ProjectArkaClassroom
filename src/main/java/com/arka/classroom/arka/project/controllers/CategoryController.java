package com.arka.classroom.arka.project.controllers;

import com.arka.classroom.arka.project.entities.Categoria;
import com.arka.classroom.arka.project.models.dto.CreateCategoryDto;
import com.arka.classroom.arka.project.services.CategoriaService;
import com.arka.classroom.arka.project.services.exception.ClientException;
import com.arka.classroom.arka.project.services.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categoria")
public class CategoryController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<List<Categoria>> findByName(@PathVariable String name) {
        try {
            List<Categoria> categorias = categoriaService.findByName(name);
            return ResponseEntity.ok(categorias);
        } catch (GeneralException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody CreateCategoryDto categoryDto) {
        try {
            Categoria savedCategoria = categoriaService.save(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoria);
        } catch (GeneralException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody CreateCategoryDto categoryDto) {
        try {
            Categoria updatedCategoria = categoriaService.update(id, categoryDto);
            return ResponseEntity.ok(updatedCategoria);
        } catch (ClientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
