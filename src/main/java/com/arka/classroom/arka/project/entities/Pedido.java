package com.arka.classroom.arka.project.entities;

import com.arka.classroom.arka.project.entities.enums.EstadoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String metodo_pago;

    @Column(name = "fecha_orden")
    private Date date;

    @Column(name = "total", precision = 10,scale = 2, nullable = false)
    private BigDecimal salePrice;

    @Enumerated(value = EnumType.STRING)
    private EstadoPedido estadoPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;



}
