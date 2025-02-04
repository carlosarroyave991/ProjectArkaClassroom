package com.arka.classroom.arka.project.entities;

import com.arka.classroom.arka.project.entities.enums.TipoUsuario;
import jakarta.persistence.*;


@Entity(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(name = "correo")
    private String email;

    @Column(name = "telefono")
    private String phone;

    @Column()
    private String dni;

    public Cliente() {
    }

    public Cliente(String name, TipoUsuario tipoUsuario, String email, String phone, String dni) {

        this.name = name;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.phone = phone;
        this.dni = dni;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
