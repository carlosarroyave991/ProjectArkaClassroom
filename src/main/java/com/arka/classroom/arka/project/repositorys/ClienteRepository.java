package com.arka.classroom.arka.project.repositorys;

import com.arka.classroom.arka.project.entities.Cliente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByName(String name); //Opcionalmente me envie un cliente por el name

    List<Cliente> findAll(Sort sort);


}
