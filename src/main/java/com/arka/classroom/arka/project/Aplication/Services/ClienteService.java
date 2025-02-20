package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import com.arka.classroom.arka.project.Domain.Entities.Cliente;
import com.arka.classroom.arka.project.Domain.Repositorys.ClienteRepository;
import com.arka.classroom.arka.project.infraestructure.Mappers.ClienteMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    ClienteMapper clienteMapper;

    public List<CreateClienteDto> getAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CreateClienteDto> findById(Long id){
        Optional<Cliente> client = clienteRepository.findById(id);
        if(client.isPresent()){
            CreateClienteDto clienteDto = clienteMapper.toDto(client.get());
            return Optional.of(clienteDto);
        }else{
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public List<CreateClienteDto> findByName(String name){
        List<Cliente> clientes = clienteRepository.findByName(name);
        if(clientes.isEmpty()){
            throw new GeneralException(NAME_NO_ENCONTRADO);
        }else{
            return clientes.stream()
                    .map(clienteMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    public CreateClienteDto save(CreateClienteDto clienteDto){
        List<Cliente> clientes = clienteRepository.findByDni(clienteDto.getDni());
        if(clientes.isEmpty()){
            Cliente cliente = clienteMapper.toEntity(clienteDto);
            cliente = clienteRepository.save(cliente);
            CreateClienteDto clienteResponse = clienteMapper.toDto(cliente);
            return clienteResponse;
        }else {
            throw new GeneralException(ID_YA_EXISTE);
        }
    }

    public CreateClienteDto update(Long id, CreateClienteDto clienteDto){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            //le pasamos el objeto encontrado y luego le pasamos los datos del dto
            Cliente existingCliente = clienteOptional.get();
            existingCliente.setName(clienteDto.getName());
            existingCliente.setTipoUsuario(clienteDto.getTipoUsuario());
            existingCliente.setEmail(clienteDto.getEmail());
            existingCliente.setPhone(clienteDto.getPhone());
            existingCliente.setDni(clienteDto.getDni());
            
            //Guardamos
            existingCliente = clienteRepository.save(existingCliente);

            //Ahora pasamos objeto cliente entidad a clienteDto para el response
            CreateClienteDto clienteResponse = clienteMapper.toDto(existingCliente);
            return clienteResponse;
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public void delete(Long id){
        Optional<Cliente> result = clienteRepository.findById(id);
        if(result.isPresent()){
            clienteRepository.deleteById(id);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }
}