package com.arka.classroom.arka.project.infraestructure.controllers;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import com.arka.classroom.arka.project.Domain.Entities.Cliente;
import com.arka.classroom.arka.project.Aplication.Services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<CreateClienteDto>> getAllClientes() {
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CreateClienteDto>> findById(@PathVariable("id")Long id){
        return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CreateClienteDto>> findByName(@RequestParam()String name){
        return new ResponseEntity<>(clienteService.findByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreateClienteDto> save(@RequestBody CreateClienteDto clienteDto){
        return new ResponseEntity<>(clienteService.save(clienteDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateClienteDto> update(
            @PathVariable("id")Long id,
            @RequestBody CreateClienteDto clienteDto
    ){
        return new ResponseEntity<>(clienteService.update(id, clienteDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        clienteService.delete(id);
        return new ResponseEntity<>("Se eliminó correctamente", HttpStatus.OK);
    }
}
