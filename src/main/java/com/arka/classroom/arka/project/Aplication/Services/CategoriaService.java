package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Domain.Entities.Categoria;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCategoriaDto;
import com.arka.classroom.arka.project.Domain.Repositorys.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final static String CATEGORIA_EXISTE = "La categoria ya existe";
    private final static String ID_CATEGORIA_NO_EXISTE = "La categoria no existe";

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()){
            return categoriaOptional;
        }else {
            throw new GeneralException(ID_CATEGORIA_NO_EXISTE);
        }
    }

    public List<Categoria> findByName(String name){
        List<Categoria> categorias = categoriaRepository.findByName(name);
        if(categorias.isEmpty()){
            throw new GeneralException(CATEGORIA_EXISTE);
        }else{
            return categorias;
        }
    }

    public Categoria update(Long id, CreateCategoriaDto categoryDto) {
        Optional<Categoria> optionalCategory = categoriaRepository.findById(id);
        if(optionalCategory.isPresent()){
            Categoria category = optionalCategory.get();
            BeanUtils.copyProperties(categoryDto, category);
            return categoriaRepository.save(category);
        } else {
            throw new GeneralException(ID_CATEGORIA_NO_EXISTE);
        }
    }


    public Categoria save(CreateCategoriaDto categoryDto) {
        List<Categoria> categoriaList = categoriaRepository.findByName(categoryDto.getName());
        if(categoriaList.isEmpty()){
            Categoria categoria = new Categoria();
            BeanUtils.copyProperties(categoryDto, categoria);
            return categoriaRepository.save(categoria);
        }else{
            throw new GeneralException(CATEGORIA_EXISTE);
        }
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
