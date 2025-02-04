package com.arka.classroom.arka.project.services;

import com.arka.classroom.arka.project.entities.Cliente;
import com.arka.classroom.arka.project.repositorys.ClienteRepository;
import com.arka.classroom.arka.project.services.exception.ClientException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ClienteService {
    private final static String NAME_YA_EXISTE = "El nickname ya existe en la base de datos";
    private final static String NAME_NO_ENCONTRADO = "El nickname no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAll(){
        return clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<Cliente> findById(Long id){
        Optional<Cliente> client = clienteRepository.findById(id);
        if(client.isPresent()){
            return client;
        }else{
            throw new ClientException(ID_NO_ENCONTRADO);
        }
    }

    public List<Cliente> findByName(String name){
        List<Cliente> client = clienteRepository.findByName(name);
        if(client.isEmpty()){
            throw new ClientException(NAME_NO_ENCONTRADO);
        }else{
            return client;
        }
    }

    public Cliente save(Cliente client){
        return clienteRepository.save(client);
    }

    public Cliente update(Cliente client){
        Optional<Cliente> result = clienteRepository.findById(client.getId());
        if(result.isPresent()){
            return clienteRepository.save(client);
        }else {
            throw new ClientException(ID_NO_ENCONTRADO);
        }
    }

    public void delete(Long id){
        Optional<Cliente> result = clienteRepository.findById(id);
        if(result.isPresent()){
            clienteRepository.deleteById(id);
        }else {
            throw new ClientException(ID_NO_ENCONTRADO);
        }
    }






}
