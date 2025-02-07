package com.arka.classroom.arka.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descipcion")
    private String description;

    @Column(name = "imagen")
    private String image;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column()
    private Date activeSince;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

}
