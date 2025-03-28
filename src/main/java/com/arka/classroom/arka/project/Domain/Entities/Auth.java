package com.arka.classroom.arka.project.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "autorizaciones")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario")
    private String username;

    @Column(name = "correo")
    private String email;

    @Column(name = "clave")
    private String password;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date createdDate;

    @JsonIgnore
    @OneToOne(mappedBy = "auth")
    private Cliente cliente;

}
