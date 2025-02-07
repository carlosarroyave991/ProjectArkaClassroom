package com.arka.classroom.arka.project.services;

import com.arka.classroom.arka.project.entities.Categoria;
import com.arka.classroom.arka.project.entities.Cliente;
import com.arka.classroom.arka.project.models.dto.CreateCategoryDto;
import com.arka.classroom.arka.project.repositorys.CategoryRepository;
import com.arka.classroom.arka.project.services.exception.ClientException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class CategoriaService {
    private final static String CATEGORIA_EXISTE = "La categoria ya existe";
    private final static String ID_CATEGORIA_NO_EXISTE = "La categoria no existe";

    @Autowired
    CategoryRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> findByName(String name){
        List<Categoria> categorias = categoriaRepository.findByName(name);
        if(categorias.isEmpty()){
            throw new ClientException(CATEGORIA_EXISTE);
        }else{
            return categorias;
        }
    }

    public Categoria update(Long id, CreateCategoryDto categoryDto) {
        Optional<Categoria> optionalCategory = categoriaRepository.findById(id);
        if(optionalCategory.isPresent()){
            Categoria category = optionalCategory.get();
            BeanUtils.copyProperties(categoryDto, category);
            return categoriaRepository.save(category);
        } else {
            throw new ClientException(ID_CATEGORIA_NO_EXISTE);
        }
    }


    public Categoria save(CreateCategoryDto categoryDto) {
        List<Categoria> categoriaList = categoriaRepository.findByName(categoryDto.getName());
        if(categoriaList.isEmpty()){
            Categoria categoria = new Categoria();
            BeanUtils.copyProperties(categoryDto, categoria);
            return categoriaRepository.save(categoria);
        }else{
            throw new ClientException(CATEGORIA_EXISTE);
        }
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
