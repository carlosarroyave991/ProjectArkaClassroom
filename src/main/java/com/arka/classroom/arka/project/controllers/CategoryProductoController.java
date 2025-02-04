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

    /*@RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @PostMapping(value = "/category")
    public Category save(@Valid @RequestBody() CreateCategoryDto dto){
        Category newCategory = new Category();
        newCategory.setName(dto.getNombre());
        newCategory.setDescription(dto.getDescription());
        newCategory.setActiveSince(new Date());
        newCategory.setImage(dto.getImagen());

        return this.categoryRepository.save(newCategory);
    }*/

}
