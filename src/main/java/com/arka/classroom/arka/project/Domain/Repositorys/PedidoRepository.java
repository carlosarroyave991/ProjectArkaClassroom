package com.arka.classroom.arka.project.Domain.Repositorys;

import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.arka.classroom.arka.project.Domain.Entities.Pedido;
import com.arka.classroom.arka.project.Domain.Entities.Producto;
import com.arka.classroom.arka.project.Domain.Entities.enums.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByReference(Long reference);

    /*@Query("SELECT new com.arka.homework.projectArka.Pedido.Aplication.Dto.HistorialPedidosDto(c.id, c.name, p.id, p.date, pr.name, cp.amount) " +
            "FROM Cliente c " +
            "JOIN c.carrito ca " +
            "JOIN ca.carritoProductos cp " +
            "JOIN cp.producto pr " +
            "JOIN ca.pedido p " +
            "WHERE c.id = :clienteId")
    List<HistorialPedidosDto> findHistorialPedidosByClienteId(@Param("clienteId") Long clienteId);
*/
    List<Pedido> findByEstadoPedido(EstadoPedido estadoPedido);

    List<Pedido> findByDateBetween(Date startDate, Date endDate);

    Optional<Pedido> findByCarrito_Id(Long id);

    /*@Query("SELECT p FROM Pedido p JOIN p.carrito c JOIN c.carritoProductos cp JOIN cp.producto pr WHERE pr.id = :productoId")
    List<Pedido> findPedidosByProductoId(@Param("productoId") Long productoId);*/
    //metodo derivado para realizar la consulta de arriba
    List<Pedido> findByCarrito_CarritoProductos_Producto_Id(Long productoId);

    List<Pedido> findByCarrito_Cliente_Id(Long clienteId);
}