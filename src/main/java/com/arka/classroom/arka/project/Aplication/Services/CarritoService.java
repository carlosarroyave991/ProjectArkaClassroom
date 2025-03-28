package com.arka.classroom.arka.project.Aplication.Services;

import com.arka.classroom.arka.project.Aplication.Services.exception.GeneralException;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.*;
import com.arka.classroom.arka.project.Domain.Repositorys.*;
import com.arka.classroom.arka.project.infraestructure.Mappers.CarritoMapper;
import com.arka.classroom.arka.project.infraestructure.Mappers.CarritoProductoMapper;
import com.arka.classroom.arka.project.infraestructure.Mappers.ProductoMapper;
import com.arka.classroom.arka.project.infraestructure.Response.CreateCarritoProductoResponse;
import com.arka.classroom.arka.project.infraestructure.Response.OnlyCarritoResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


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

    @Autowired
    ProductoMapper productoMapper;


    public List<OnlyCarritoResponse> getAll() {
        List<Carrito> carritos = carritoRepository.findAll();

        return carritoMapper.carritoToResponse(carritos);
    }

    public Optional<Carrito> findById(Long id) {
        Optional<Carrito> producto = carritoRepository.findById(id);
        if (producto.isPresent()) {
            return producto;
        } else {
            throw new GeneralException(ID_NO_ENCONTRADO);
        }
    }


    /**
     * Funcion para crear el carrito de compras
     * @return retorna la creacion de un carrito con sus productos
     */
    @Transactional
    public List<CreateCarritoProductoResponse> crearCarrito(CreateCarritoDto carritoDto) {
        // 1) Buscar al cliente
        Cliente cliente = clienteRepository.findById(carritoDto.getCliente().getId())
                .orElseThrow(() -> new GeneralException(ID_CLIENTE_NO_ENCONTRADO));

        // 2) Verificar el stock de todos los productos
        for (CreateCarritoProductoDto createCarritoProductoDto : carritoDto.getCarritoProductos()) {
            Optional<Producto> productoOptional = productoRepository.findById(createCarritoProductoDto.getProductos().getId());
            if (productoOptional.isPresent()) {
                Producto producto = productoOptional.get();
                int nuevoStock = producto.getStock() - createCarritoProductoDto.getAmount();

                if (nuevoStock < 0) {
                    throw new GeneralException("Stock insuficiente para el producto: " + producto.getName());
                }
            } else {
                throw new GeneralException(PRODUCTO_NO_ENCONTRADO);
            }
        }

        // 3) Crear el carrito
        Carrito carrito = new Carrito();
        carrito.setCliente(cliente);
        carrito.setCreatedDate(new Date());

        // Guardamos el carrito y aseguramos que tenga un ID
        carrito = carritoRepository.save(carrito);

        // Verificar que el carrito tiene un ID
        if (carrito.getId() == null) {
            throw new GeneralException("El carrito no tiene un ID después de ser guardado.");
        }

        // 4) Crear los CarritoProducto y asociarlos al carrito, y actualizar el stock de productos
        List<CarritoProducto> carritoProductoList = new ArrayList<>();
        for (CreateCarritoProductoDto createCarritoProductoDto : carritoDto.getCarritoProductos()) {
            Optional<Producto> productoOptional = productoRepository.findById(createCarritoProductoDto.getProductos().getId());
            if (productoOptional.isPresent()) {
                Producto producto = productoOptional.get();

                // Crear una nueva instancia de CarritoProducto
                CarritoProducto carritoProducto = new CarritoProducto();
                carritoProducto.setProducto(producto);
                carritoProducto.setAmount(createCarritoProductoDto.getAmount());
                carritoProducto.setCarrito(carrito);

                // Actualizar el stock del producto
                int nuevoStock = producto.getStock() - createCarritoProductoDto.getAmount();
                producto.setStock(nuevoStock);
                productoRepository.save(producto); // Guardar el producto con el stock actualizado

                carritoProductoList.add(carritoProducto);
            } else {
                throw new GeneralException(PRODUCTO_NO_ENCONTRADO);
            }
        }

        // 5) Guardar los nuevos CarritoProducto en el repositorio
        carritoProductoRepository.saveAll(carritoProductoList);

        // 6) Asociar la lista de CarritoProducto al carrito y guardarlo nuevamente
        carrito.setCarritoProductos(carritoProductoList);
        carrito = carritoRepository.save(carrito);

        // 7) Convertir a CreateCarritoProductoResponse y devolver una respuesta
        List<CreateCarritoProductoResponse> response = carritoProductoMapper.carritoProductoToCreateCarritoProductoResponse(carritoProductoList);

        return response;
    }


    /**
     * NoService que me traera la informacion de los productos
     * @param carritoProductoDto
     * @return
     */
    private List<CarritoProducto> getProducto(List<CreateCarritoProductoDto> carritoProductoDto){
        List<CarritoProducto> carritoProductos = new ArrayList<>();

        // Iterar sobre cada objeto CreateCarritoProductoDto en la lista carritoProductoDto
        for (CreateCarritoProductoDto createCarritoProductoDto : carritoProductoDto) {
            // Verificación nula para productos
            if (createCarritoProductoDto.getProductos() != null) {
                Optional<Producto> producto = productoRepository.findById(createCarritoProductoDto.getProductos().getId());
                // Verificar si el producto está presente
                if(producto.isPresent()){
                    // Crear una nueva instancia de CarritoProducto para cada producto
                    CarritoProducto newCarritoProducto = new CarritoProducto();
                    newCarritoProducto.setProducto(producto.get());
                    newCarritoProducto.setAmount(createCarritoProductoDto.getAmount());

                    carritoProductos.add(newCarritoProducto);
                } else {
                    // Lanzar una excepción si el producto no es encontrado
                    throw new GeneralException(PRODUCTO_NO_ENCONTRADO);
                }
            } else {
                // Manejar el caso de lista de productos nula
                System.out.println("Productos es nulo para CreateCarritoProductoDto con ID: " + createCarritoProductoDto.getId());
            }
        }

        return carritoProductos;
    }


    /**
     * Funcion que agregara productos al carrito
     * @param carritoId
     * @param productoId
     * @return
     */
    public Carrito agregarProductoAlCarrito(Long carritoId, Long productoId) {
        Carrito carrito = carritoRepository.findById(carritoId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        CarritoProducto carritoProducto = new CarritoProducto();
        carritoProducto.setCarrito(carrito);
        carritoProducto.setProducto(producto);

        carrito.getCarritoProductos().add(carritoProducto);
        return carritoRepository.save(carrito);
    }

    /**
     * Funcion que permite actualizar un carrtio por medio del id
     * @param carritoDto nuevo objeto
     * @return
     */
    public CreateCarritoDto updateCarrito(CreateCarritoDto carritoDto) {
        // 1) Buscar el carrito existente
        Carrito carrito = carritoRepository.findById(carritoDto.getId())
                .orElseThrow(() -> new GeneralException(CARRITO_NO_ENCONTRADO));

        // 2) Verificar si el carrito tiene un pedido asociado
        Optional<Pedido> pedidoOptional = pedidoRepository.findByCarrito_Id(carrito.getId());
        if (pedidoOptional.isPresent()) {
            throw new GeneralException("El carrito tiene un pedido asociado y no puede ser actualizado.");
        }

        // 3) Actualizar la información del carrito
        Cliente cliente = clienteRepository.findById(carritoDto.getCliente().getId())
                .orElseThrow(() -> new GeneralException(ID_CLIENTE_NO_ENCONTRADO));
        carrito.setCliente(cliente);
        carrito.setCreatedDate(carritoDto.getCreatedDate() != null ? carritoDto.getCreatedDate() : carrito.getCreatedDate());

        // 4) Eliminar los CarritoProducto antiguos
        carritoProductoRepository.deleteAllByCarritoId(carrito.getId());

        // 5) Crear los nuevos CarritoProducto y asociarlos al carrito
        List<CarritoProducto> carritoProductoList = getProducto(carritoDto.getCarritoProductos());
        for (CarritoProducto cp : carritoProductoList) {
            cp.setCarrito(carrito);
        }

        // 6) Guardar los nuevos CarritoProducto en el repositorio
        carritoProductoRepository.saveAll(carritoProductoList);

        // 7) Asociar la lista de carritoProducto al carrito y guardarlo nuevamente
        carrito.setCarritoProductos(carritoProductoList);
        carrito = carritoRepository.save(carrito);

        // 8) Convertir a CreateCarritoDto y devolver la respuesta
        CreateCarritoDto response = carritoMapper.toDto(carrito);

        return response;
    }

    /**
     * Servicio que consulta todos los carritos abandonados
     * @return retorna una lista de carritos abandonados despues de dos horas
     */
    public List<Carrito> obtenerCarritosAbandonados(){
            // Calcular la fecha de corte para considerar un carrito abandonado (por ejemplo, 7 días atrás)
            Calendar calendar = Calendar.getInstance();
            //calendar.add(Calendar.DAY_OF_YEAR, -7); excepcion de 7 dias
            calendar.add(Calendar.HOUR_OF_DAY, -2); //excepcion de 2 horas
            Date thresholdDate = calendar.getTime();

            // Obtener los carritos abandonados utilizando el metodo derivado
            return carritoRepository.findByPedidoIsNullAndCreatedDateBefore(thresholdDate);
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

