package com.arka.classroom.arka.project.services;

import com.arka.classroom.arka.project.entities.Producto;
import com.arka.classroom.arka.project.repositorys.ProductoRepository;
import com.arka.classroom.arka.project.services.exception.ProductException;
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
public class ProductoService {
    private final static String PRODUCT_YA_EXISTE = "El producto ya existe en la base de datos";
    private final static String PRODUCT_NO_ENCONTRADO = "El producto no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getAll(){
        return productoRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<Producto> findById(Long id){
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            return producto;
        }else{
            throw new ProductException(ID_NO_ENCONTRADO);
        }
    }

    public List<Producto> findByName(String name){
        List<Producto> producto = productoRepository.findByName(name);
        if (producto.isEmpty()){
            throw new ProductException(PRODUCT_NO_ENCONTRADO);
        }else{
            return producto;
        }
    }

    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto update(Producto producto){
        Optional<Producto> result = productoRepository.findById(producto.getId());
        if(result.isPresent()){
            return productoRepository.save(producto);
        }else {
            throw new ProductException(ID_NO_ENCONTRADO);
        }
    }


    public void delete(Long id){
        Optional<Producto> result = productoRepository.findById(id);
        if(result.isPresent()){
            productoRepository.deleteById(id);
        }else {
            throw new ProductException(ID_NO_ENCONTRADO);
        }
    }

    public List<Producto> getProductosPorRangoDePrecio(Float minPrice, Float maxPrice){
        return productoRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
