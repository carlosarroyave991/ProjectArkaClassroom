package com.arka.classroom.arka.project.Domain.Entities;

import com.arka.classroom.arka.project.Domain.Entities.enums.EstadoPedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "referencia")
    private Long reference;

    @Column(name = "fecha_orden")
    private Date date;

    @Column(name = "total", precision = 10,scale = 2, nullable = false)
    private BigDecimal salePrice;

    @Enumerated(value = EnumType.STRING)
    private EstadoPedido estadoPedido;

    /*@ManyToOne
    @JoinColumn(name = "cliente_id",nullable = false)
    @JsonBackReference
    private Cliente cliente;*/

    @OneToOne
    @JoinColumn(name = "carrito_id",nullable = false)
    @JsonBackReference
    private Carrito carrito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetodo_pago() {
        return metodoPago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodoPago = metodo_pago;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    /*public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
*/

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
}