package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.arka.classroom.arka.project.Aplication.utils.ReferenceGenerator;
import com.arka.classroom.arka.project.Domain.Entities.*;
import com.arka.classroom.arka.project.Domain.Entities.enums.EstadoPedido;
import com.arka.classroom.arka.project.Domain.Repositorys.*;
import com.arka.classroom.arka.project.infraestructure.Feign.NotificationClient;
import com.arka.classroom.arka.project.infraestructure.Mappers.PedidoMapper;
import com.arka.classroom.arka.project.infraestructure.Request.NotificacionRequest;
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
    private final static String CARRITO_NOT_FOUND = "El carrito no fue encontrado en la base de datos.";
    private final static String PEDIDO_NOT_FOUND = "El pedido no fue encontrado en la base de datos.";

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CarritoService carritoService;

    @Autowired
    NotificationClient notificationClient;

    @Autowired
    PedidoMapper pedidoMapper;

    public List<CreatePedidoDto> getAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoMapper.toListDto(pedidos);
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

    public Optional<Pedido> findByCarrito_Id(Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findByCarrito_Id(id);
        if (pedidoOptional.isPresent()){
            return pedidoOptional;
        }else {
            throw new GeneralException(CARRITO_NOT_FOUND);
        }
    }

    /**
     * Esta funcion hara la consulta de buscar el estado de los pedidos
     */
    public List<CreatePedidoDto> findByEstadoPedido(EstadoPedido estadoPedido){
        List<Pedido> pedidoList = pedidoRepository.findByEstadoPedido(estadoPedido);
        if (pedidoList.isEmpty()) {
            throw new GeneralException(ESTADO_NO_ENCONTRADO);
        }else{
            return pedidoMapper.toListDto(pedidoList);
        }
    }

    /**
     * Funcion que creara un pedido y le asociara un carrito
     * @param pedidoDto
     * @return
     */
    public CreatePedidoDto createPedido(CreatePedidoDto pedidoDto){
        Optional<Carrito> carritoOptional = carritoRepository.findById(pedidoDto.getCarrito().getId());
        if (carritoOptional.isEmpty()){
            throw new GeneralException(CARRITO_NOT_FOUND);
        }

        Carrito carrito = carritoOptional.get();
        BigDecimal amountValue = BigDecimal.ZERO;
        BigDecimal salePrice = BigDecimal.ZERO;

        for (CarritoProducto carritoProducto : carrito.getCarritoProductos()) {
            amountValue = amountValue.add(BigDecimal.valueOf(carritoProducto.getAmount()));
            salePrice = salePrice.add(carritoProducto.getProducto().getPrice().multiply(BigDecimal.valueOf(carritoProducto.getAmount())));
        }

        Pedido newPedido = new Pedido();
        newPedido.setMetodoPago(pedidoDto.getMetodoPago());
        newPedido.setReference(ReferenceGenerator.generateReference());
        newPedido.setDate(pedidoDto.getDate() != null ? pedidoDto.getDate() : new Date());
        newPedido.setAmountValue(amountValue); // Total de los productos
        newPedido.setSalePrice(salePrice); //Suma total del precio de todos los productos
        newPedido.setEstadoPedido(pedidoDto.getEstadoPedido() != null ? pedidoDto.getEstadoPedido().toString() : "pendiente");
        newPedido.setCarrito(carritoOptional.get());

        // Actualizar el stock de los productos si el estado del pedido es "procesado"
        if (newPedido.getEstadoPedido() == EstadoPedido.procesado) {
            for (CarritoProducto carritoProducto : carrito.getCarritoProductos()) {
                Producto producto = carritoProducto.getProducto();
                producto.setStock(producto.getStock() - carritoProducto.getAmount());
                productoRepository.save(producto); // Guardar los cambios en el repositorio de productos
            }
        }

        pedidoRepository.save(newPedido);
        return pedidoMapper.toDto(newPedido);
    }

    /**
     * Funcion para actualizar cualquier informacion del pedido
     * @param pedidoDto
     * @return
     */
    public CreatePedidoDto updatePedido(CreatePedidoDto pedidoDto){
        Optional<Carrito> carritoOptional = Optional.ofNullable(carritoRepository.findById(pedidoDto.getCarrito().getId()).orElseThrow(() -> new GeneralException(CARRITO_NOT_FOUND)));
        Optional<Pedido> pedidoOptional = Optional.ofNullable(pedidoRepository.findById(pedidoDto.getId()).orElseThrow(() -> new GeneralException(PEDIDO_NOT_FOUND)));

        Pedido newPedido = pedidoOptional.get();
        newPedido.setMetodoPago(Optional.ofNullable(pedidoDto.getMetodoPago()).orElse(newPedido.getMetodoPago()));
        newPedido.setReference(Optional.ofNullable(pedidoDto.getReference()).orElse(newPedido.getReference()));
        newPedido.setDate(Optional.ofNullable(pedidoDto.getDate()).orElse(pedidoDto.getDate()));
        newPedido.setAmountValue(Optional.ofNullable(pedidoDto.getAmountValue()).orElse(pedidoDto.getAmountValue())); // Valor predeterminado
        newPedido.setSalePrice(Optional.ofNullable(pedidoDto.getSalePrice()).orElse(pedidoDto.getSalePrice()));

        newPedido.setEstadoPedido(Optional.ofNullable(pedidoDto.getEstadoPedido())
                .map(Enum::name)
                .map(String::toLowerCase)
                .orElse("pendiente"));
        // Actualizar el carrito solo si el nuevo carrito no es null, de lo contrario, dejar el valor anterior
        if (pedidoDto.getCarrito() != null) {
            newPedido.setCarrito(carritoOptional.get());
        }

        // Actualizar el stock de los productos si el estado del pedido es "procesado"
        if (newPedido.getEstadoPedido() == EstadoPedido.procesado) {
            for (CarritoProducto carritoProducto : newPedido.getCarrito().getCarritoProductos()) {
                Producto producto = carritoProducto.getProducto();
                producto.setStock(producto.getStock() - carritoProducto.getAmount());
                productoRepository.save(producto); // Guardar los cambios en el repositorio de productos
            }
        }

        pedidoRepository.save(newPedido);
        return pedidoMapper.toDto(newPedido);
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

    /**
     * funcion que trae la lista de pedidos que contienen un producto
     * @param productoId del producto en especifico
     * @return retorna una lista
     */
    public List<Pedido> getPedidosByProductoId(Long productoId) {
        return pedidoRepository.findByCarrito_CarritoProductos_Producto_Id(productoId);
    }

    /**
     * funcion que trae una lista de pedidos que pertenecen a un cliente
     * @param clienteId del cliente en especifico
     * @return retorna una lista
     */
    public List<Pedido> getPedidosByCliente(Long clienteId){
        return pedidoRepository.findByCarrito_Cliente_Id(clienteId);
    }

    /**
     * Servicio que actualizar el estado del pedido y envia notificacion
     * @param pedidoId
     * @param nuevoEstado
     */
    public void updateEstadoPedido(Long pedidoId, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new GeneralException("Pedido no encontrado"));

        pedido.setEstadoPedido(String.valueOf(nuevoEstado));
        pedidoRepository.save(pedido);

        // Enviar notificación
        NotificacionRequest notificationRequest = new NotificacionRequest();
        notificationRequest.setTo(pedido.getCarrito().getCliente().getEmail());
        notificationRequest.setSubject("Actualización de estado de pedido");
        notificationRequest.setBody("El estado de su pedido ha sido actualizado a: " + nuevoEstado);

        notificationClient.sendNotification(notificationRequest);
    }

    public void deleteById(Long id){
        pedidoRepository.deleteById(id);
    }
}
