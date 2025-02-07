package com.arka.classroom.arka.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto")
    private String name;

    @Column(name = "marca")
    private String stamp;

    @Column(name = "precio", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column()
    private Integer stock;

    @OneToMany(mappedBy = "producto")
    private List<CarritoProducto> carritoProductoList;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "producto_proveedor",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "proveedor_id")
    )
    private List<Proveedor> proveedores;

}
