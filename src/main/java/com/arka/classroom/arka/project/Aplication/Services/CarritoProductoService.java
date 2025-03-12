package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.Domain.Repositorys.CarritoProductoRepository;
import com.arka.classroom.arka.project.Domain.Repositorys.CarritoRepository;
import com.arka.classroom.arka.project.Domain.Repositorys.ClienteRepository;
import com.arka.classroom.arka.project.infraestructure.Mappers.CarritoProductoMapper;
import com.arka.classroom.arka.project.infraestructure.Mappers.ProductoMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CarritoProductoService {
    private final static String CARRITO_YA_EXISTE = "El carrito ya existe en la base de datos";
    private final static String CARRITO_NO_ENCONTRADO = "El carrito no fue encontrado";
    private final static String PRODUCTO_NO_ENCONTRADO = "El producto no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_CLIENTE_NO_ENCONTRADO = "El Id del cliente no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    CarritoProductoRepository carritoProductoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    CarritoProductoMapper carritoProductoMapper;

    @Autowired
    ProductoMapper productoMapper;

    public void deleteAllByCarritoId(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new GeneralException(CARRITO_NO_ENCONTRADO));
        carritoProductoRepository.deleteAllByCarritoId(carrito.getId());
        carritoRepository.delete(carrito);
    }

}
