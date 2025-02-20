package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.Domain.Entities.Pedido;
import com.arka.classroom.arka.project.Domain.Entities.enums.EstadoPedido;
import com.arka.classroom.arka.project.Domain.Repositorys.CarritoRepository;
import com.arka.classroom.arka.project.Domain.Repositorys.ClienteRepository;
import com.arka.classroom.arka.project.Domain.Repositorys.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final static String PRODUCT_YA_EXISTE = "El producto ya existe en la base de datos";
    private final static String ESTADO_NO_ENCONTRADO = "El estado no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CarritoRepository carritoRepository;

    public List<Pedido> getAll(){
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id){
        Optional<Pedido> pedidoOpcional = pedidoRepository.findById(id);
        if (pedidoOpcional.isPresent()){
            return pedidoOpcional;
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    public Optional<Pedido>findByReference(Long reference){
        Optional<Pedido> pedidoOpcional = pedidoRepository.findByReference(reference);
        if (pedidoOpcional.isPresent()){
            return pedidoOpcional;
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }

    /**
     * Esta funcion hara la consulta de buscar el estado de los pedidos
     */
    public List<Pedido> findByEstadoPedido(EstadoPedido estadoPedido){
        List<Pedido> pedidoList = pedidoRepository.findByEstadoPedido(estadoPedido);
        if (pedidoList.isEmpty()) {
            throw new GeneralException(ESTADO_NO_ENCONTRADO);
        }else{
            return pedidoList;
        }
    }

    /**
     * Consulta del historial de pedidos
     *
    public List<HistorialPedidosDto> obtenerHistorialPedidos(Long clienteId) {
        return pedidoRepository.findHistorialPedidosByClienteId(clienteId);
    }*/

    /**
     * Funcion para obtener los carritos o pedidos abandonados abandonados
     */
    /*public List<Pedido> findByEstadoPedido(){
        List<Pedido> pedidoList = pedidoRepository.findByEstadoPedido(EstadoPedido.Abandonado);
        return pedidoList;
    }*/

    /**
     * Funcion para buscar los pedidos en un rango de fecha
     * @param start se utilizar para la fecha inicial de busqueda
     * @param end se utiliza para la fecha final de busqueda
     * @return
     */
    public List<Pedido> findByDateBetween(Date start, Date end){
        return pedidoRepository.findByDateBetween(start, end);
    }

    public Pedido save(CreatePedidoDto createPedidoDto){
        //primero valida si existe un carrito
        Optional<Carrito> carritoOptional = carritoRepository.findById(createPedidoDto.getCarrito().getId());
        //si existe asignemelo, sino lance un error
        if (carritoOptional.isEmpty()){
            throw new GeneralException(ID_NO_ENCONTRADO);
        }

        Carrito carrito = carritoOptional.get();

        //Calcular el precio total de los productos en el carrito
        BigDecimal salePrice = carrito.getCarritoProductos().stream()
                .map(cp -> cp.getProducto().getPrice().multiply(BigDecimal.valueOf(cp.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(createPedidoDto,pedido);
        pedido.setCarrito(carrito);

        //Establecer el salePrice calculado
        pedido.setSalePrice(salePrice);

        //Asigna el id del pedido en el carrito
        carrito.setPedido(pedido);

        //Actualiza el carrito
        carritoRepository.save(carrito);

        return pedidoRepository.save(pedido);
    }


    public void deleteById(Long id){
        pedidoRepository.deleteById(id);
    }
}
