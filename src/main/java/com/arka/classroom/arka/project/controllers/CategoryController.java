package com.arka.classroom.arka.project.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class CategoryController {

    /*@Autowired
    CategoryRepository categoryRepository;

    private List<CreateCategoryDto> categorias;

    public CategoryController() {
        this.categorias = new ArrayList<>();
        categorias.add(new CreateCategoryDto((long) 1L,"camara web","nueva, la mejor","imagen"));
        categorias.add(new CreateCategoryDto((long) 2L,"computadora","lorem insup","imagen"));
        categorias.add(new CreateCategoryDto((long) 3L,"smartwatch","carros voladores","imagen"));
    }

    //@GetMapping(value = "/api/welcome")
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<CreateCategoryDto> getAll(){
        return categorias;
    }

    *//**
     * Query paths
     * *//*
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<CreateCategoryDto> getById(@PathVariable(value = "id")Long id){
        //busca por id
        Optional<CreateCategoryDto> dato = categorias.stream()
                .filter(categoria -> categoria.getId() == id)
                .findFirst();
        return dato.map(createCategoryDto -> new ResponseEntity<>(createCategoryDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    *//**
     * Query params
     * *//*
    @GetMapping(value = "/category/search")
    public List<CreateCategoryDto> search(
            @RequestParam(name = "nombre", required = false)String nombre){
        if(nombre != null && !nombre.isEmpty()){
            return categorias.stream()
                    .filter(categoria -> categoria.getNombre().equalsIgnoreCase(nombre))
                    .collect(Collectors.toList());
        }
        return categorias;
    }*/

}
