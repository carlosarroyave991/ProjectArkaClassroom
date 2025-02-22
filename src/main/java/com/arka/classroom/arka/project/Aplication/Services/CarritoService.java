package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoProductoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.Domain.Entities.CarritoProducto;
import com.arka.classroom.arka.project.Domain.Entities.Cliente;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.arka.classroom.arka.project.Domain.Repositorys.*;
import com.arka.classroom.arka.project.infraestructure.Mappers.CarritoMapper;
import com.arka.classroom.arka.project.infraestructure.Mappers.CarritoProductoMapper;
import com.arka.classroom.arka.project.infraestructure.Response.CreateCarritoProductoResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CarritoService {
    private final static String CARRITO_YA_EXISTE = "El carrito ya existe en la base de datos";
    private final static String CARRITO_NO_ENCONTRADO = "El carrito no fue encontrado";
    private final static String PRODUCTO_NO_ENCONTRADO = "El producto no fue encontrado";
    private final static String ID_NO_ENCONTRADO = "El Id no fue encontrado";
    private final static String ID_CLIENTE_NO_ENCONTRADO = "El Id del cliente no fue encontrado";
    private final static String ID_YA_EXISTE = "El Id ya existe en la base de datos";

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    CarritoProductoRepository carritoProductoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CarritoProductoMapper carritoProductoMapper;

    @Autowired
    CarritoMapper carritoMapper;

    public List<CreateCarritoDto> getAll(){
        List<Carrito> carritos = carritoRepository.findAll();
        return carritos.stream()
                .map(carritoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<Carrito> findById(Long id){
        Optional<Carrito> producto = carritoRepository.findById(id);
        if(producto.isPresent()){
            return producto;
        }else{
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }


    /**
     * Funcion que llenara el carrito de compras
     * @return retorna la entidad carritoProductos con los datos necesarios
     * @throws GeneralException Indica que el id no ha sido encontrado
     */
    public CarritoProducto agregarProductoAlCarrito(Long carritoId, Long productoId, Integer cantidad){
        Optional<Carrito> carritoOptional = carritoRepository.findById(carritoId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        if (carritoOptional.isEmpty()) {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }

        if (productoOptional.isEmpty()) {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }

        Carrito carrito = carritoOptional.get();
        Producto producto = productoOptional.get();

        //Verifica si el producto ya esta en el carrito
        Optional<CarritoProducto> carritoProductoOptional = carritoRepository.findByCarritoAndProducto(carrito, producto);
        CarritoProducto carritoProducto;
        if (carritoProductoOptional.isPresent()) {
            // Si el producto ya está en el carrito, actualizar la cantidad
            carritoProducto = carritoProductoOptional.get();
            carritoProducto.setAmount(carritoProducto.getAmount() + cantidad);
        } else {
            // Si el producto no está en el carrito, crear una nueva relación
            carritoProducto = new CarritoProducto();
            carritoProducto.setProducto(producto);
            carritoProducto.setCarrito(carrito);
            carritoProducto.setAmount(cantidad);
            carritoProducto.setCreatedDate(new Date());
        }
        return carritoProductoRepository.save(carritoProducto);
    }

    /**
     * Funcion que lista los productos del carrito
     * @param carritoId tiene el id del carrito a listarle los productos
     * @throws GeneralException trae un mensaje de advertencia
     * */
    public List<CreateProductoDto> listarProductosByCarritoId(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new GeneralException(ID_NO_ENCONTRADO));

        List<CreateProductoDto> productosDto = carrito.getCarritoProductos().stream()
                .map(carritoProducto -> carritoMapper.productoToDto(carritoProducto.getProducto()))
                .collect(Collectors.toList());

        return productosDto;
    }

    /**
     * Funcion que me permite guardar un carrito
     * @param carritoProductoDto el objeto que tiene la data
     * @return un dto mapeado
     */
    public CreateCarritoProductoResponse save(CreateCarritoProductoDto carritoProductoDto){
        // 1) creamos el carrito
        Carrito carrito = CreateCarrito(carritoProductoDto.getCarrito());
        // 2) obtenemos el primer producto
        Producto producto = getProductosToCarrito(carritoProductoDto.getProducto());
        // 3) creamos el carritoProducto y le pasamos la informacion obtenida
        CarritoProducto newCarritoProducto = new CarritoProducto();
        newCarritoProducto.setCarrito(carrito);
        newCarritoProducto.setProducto(producto);
        newCarritoProducto.setCreatedDate(new Date());
        newCarritoProducto.setAmount(carritoProductoDto.getAmount());

        carritoProductoRepository.save(newCarritoProducto);

        CreateCarritoProductoResponse carritoProductoResponse = carritoProductoMapper.carritoProductoToCreateCarritoProductoResponse(newCarritoProducto);
        return carritoProductoResponse;
    }

    /**
     * Funcion que me crea el carrito y le asigna un cliente
     * @param carritoDto objeto que lleva el id del cliente y el carrito
     * @return retorna el objeto carrito creado
     */
    public Carrito CreateCarrito(CreateCarritoDto carritoDto){
        Cliente cliente = getCliente(carritoDto.getCliente().getId());
        Carrito carrito = new Carrito();
        carrito.setCliente(cliente);
        return carritoRepository.save(carrito);
    }

    /**
     * Funcion para obtener un cliente en especifico
     * @param clienteId se encuentran el id del cliente
     * @return retorna el objeto cliente en caso de que exista
     */
    public Cliente getCliente(Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new GeneralException(ID_CLIENTE_NO_ENCONTRADO));
    }

    /**
     * Funcion para obtener un producto en especifico
     * @param productoDto objeto donde se encuentra la info del producto
     * @return retorna el objeto producto en caso de que exista
     */
    public Producto getProductosToCarrito(CreateProductoDto productoDto){
        return productoRepository.findById(productoDto.getId())
                .orElseThrow(() -> new GeneralException(PRODUCTO_NO_ENCONTRADO));
    }

    public Carrito update(Long id,CreateCarritoDto carritoDto){
        Optional<Carrito> carritoOptional = carritoRepository.findById(id);
        if(carritoOptional.isPresent()){
            Carrito carrito = new Carrito();
            BeanUtils.copyProperties(carritoDto, carrito);
            return carritoRepository.save(carrito);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }


    public void delete(Long id){
        Optional<Carrito> carritoOptional = carritoRepository.findById(id);
        if(carritoOptional.isPresent()){
            carritoRepository.deleteById(id);
        }else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }
}

