package com.arka.classroom.arka.project.infraestructure.controllers;

import com.arka.classroom.arka.project.Aplication.Services.PedidoService;
import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import com.arka.classroom.arka.project.Domain.Entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    /**
     * Metodo Get para traer una lista de todos los pedidos
     * @return retorna una lista
     */
    @GetMapping("/pedidos")
    public ResponseEntity<List<CreatePedidoDto>> getAll(){
        return new ResponseEntity<>(pedidoService.getAll(), HttpStatus.OK);
    }

    /**
     * Metodo Get que retorna un pedido segun su referencia
     * @param reference
     * @return
     */
    @GetMapping("/pedido/{reference}")
    public ResponseEntity<Optional<Pedido>> getPedidoByReference(@PathVariable("reference")Long reference){
        return new ResponseEntity<>(pedidoService.findByReference(reference), HttpStatus.OK);
    }

    /**
     * Metodo Get que retorna una lista de pedidos segun el producto
     * @param productoId
     * @return retorna una lista
     */
    @GetMapping("/pedidos/producto/{productoId}")
    public ResponseEntity<List<Pedido>> getPedidosByProductoId(@PathVariable("productoId")Long productoId) {
        return new ResponseEntity<>(pedidoService.getPedidosByProductoId(productoId), HttpStatus.OK);
    }

    /**
     * Metodo Get que retorna el historial de pedidos de un cliente
     * @param clienteId id del cliente
     * @return retorna una lista
     */
    @GetMapping("/pedidos/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> getPedidoByClienteId(@PathVariable("clienteId")Long clienteId){
        return new ResponseEntity<>(pedidoService.getPedidosByCliente(clienteId), HttpStatus.OK);
    }


    /**
     * Metodo Get que retorna una lista de pedidos producitos entre las fechas establecidas
     * @param start fecha inicial de la consulta
     * @param end fecha final de la consulta
     * @return retona una lista
     */
    @GetMapping("/pedidos/entre-fechas")
    public ResponseEntity<List<Pedido>> getPedidoEntreFechas(@RequestParam("start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
                                                             @RequestParam("end")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end){
       List<Pedido> pedidos = pedidoService.findByDateBetween(start, end);
       return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    /**
     * Metodo Post para crear un pedido
     * @param pedidoDto
     * @return
     */
    @PostMapping("/pedido")
    public ResponseEntity<CreatePedidoDto> createPedido(@RequestBody CreatePedidoDto pedidoDto){
        return new ResponseEntity<>(pedidoService.createPedido(pedidoDto), HttpStatus.CREATED);
    }

    /**
     * Metodo Post para actualizar un pedido
     * @param pedidoDto
     * @return
     */
    @PostMapping("/pedido/update")
    public ResponseEntity<CreatePedidoDto> updatePedido(@RequestBody CreatePedidoDto pedidoDto){
        return new ResponseEntity<>(pedidoService.updatePedido(pedidoDto), HttpStatus.OK);
    }

}
