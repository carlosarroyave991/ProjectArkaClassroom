package com.arka.classroom.arka.project.infraestructure.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class MarcaController {

    /*@Autowired
    MarcaRepository marcaRepository;

    @GetMapping("/marca")
    public Page<Marca> getAll(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.marcaRepository.findAll(pageable);
    }


    @GetMapping("/marca/findByCountrys")
    public Page<Marca> searchCountry(@RequestParam int page, @RequestParam int size, @RequestBody Country country){
        Pageable pageable = PageRequest.of(page, size);
        return this.marcaRepository.findAll(pageable, country);
    }

    @PostMapping("/marca")
    public Marca create(@RequestBody CreateMarcaDto dto){
        Marca newMarca = new Marca();
        newMarca.setName(dto.getName());
        newMarca.setDescription(dto.getDescription());
        newMarca.setLogo(dto.getLogo());
        newMarca.setCountry(dto.getCountry());
        return marcaRepository.save(newMarca);
    }*/
}
