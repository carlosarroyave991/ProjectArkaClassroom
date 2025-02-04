package com.arka.classroom.arka.project.controllers;

import com.arka.classroom.arka.project.entities.Cliente;
import com.arka.classroom.arka.project.services.ClienteService;
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
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> findById(@PathVariable("id")Long id){
        return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> findByName(@RequestParam()String name){
        return new ResponseEntity<>(clienteService.findByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente){
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente){
        return new ResponseEntity<>(clienteService.update(cliente), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        clienteService.delete(id);
        return new ResponseEntity<>("Se eliminó correctamente", HttpStatus.OK);
    }

}
