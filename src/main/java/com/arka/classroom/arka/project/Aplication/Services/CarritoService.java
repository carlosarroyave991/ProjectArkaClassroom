package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoProductoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Carrito;
import com.arka.classroom.arka.project.Domain.Entities.CarritoProducto;
import com.arka.classroom.arka.project.Domain.Entities.Cliente;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.arka.classroom.arka.project.Domain.Repositorys.*;
import com.arka.classroom.arka.project.infraestructure.Mappers.CarritoMapper;
import com.arka.classroom.arka.project.infraestructure.Mappers.CarritoProductoMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
     * @param carritoDto el objeto que tiene la data
     * @return un dto mapeado
     */
    public Carrito save(CreateCarritoDto carritoDto){
        // 1) Buscar cliente y verificar existencia
        Optional<Cliente> clienteOptional = clienteRepository.findById(carritoDto.getCliente().getId());
        if (clienteOptional.isEmpty()) {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
        Cliente cliente = clienteOptional.get();

        // 2) Convertir Dto a entidad Carrito usando el mapper
        Carrito carrito = CarritoMapper.INSTANCE.toEntity(carritoDto);

        // 3) Al carrito le pasamos al cliente encontrado
        carrito.setCliente(cliente);

        // 4) Crear y agregar los productos al carrito
        List<CarritoProducto> carritoProductosList = carritoDto.getCarritoProductos().stream()
                .map(dto -> {
                    List<Producto> productos = productoRepository.findByName(dto.getProducto().getName());
                    if (productos.isEmpty()) {
                        throw new GeneralException(PRODUCTO_NO_ENCONTRADO);
                    }
                    // Asumir que se toma el primer producto de la lista
                    Producto producto = productos.get(0);
                    CarritoProducto carritoProducto = carritoProductoMapper.createCarritoProductoDtoToCarritoProducto(dto);
                    carritoProducto.setProducto(producto);
                    carritoProducto.setCarrito(carrito);
                    return carritoProducto;
                }).collect(Collectors.toList());

        carrito.setCarritoProductos(carritoProductosList);


        // 5) Guardar el carrito y los productos
        Carrito savedCarrito = carritoRepository.save(carrito);
        carritoProductosList.forEach(carritoProductoRepository::save);

        return savedCarrito;
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

