package com.arka.classroom.arka.project.repositorys;

import com.arka.classroom.arka.project.entities.Cliente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByName(String name); //Opcionalmente me envie un cliente por el name

    List<Cliente> findAll(Sort sort);


}
