package com.arka.classroom.arka.project.Domain.Entities;

import com.arka.classroom.arka.project.Domain.Entities.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "clientes")
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

    @OneToOne
    @JoinColumn(name = "auth_id", nullable = false)
    @JsonBackReference
    private Auth auth;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Carrito> carritos;

    public Cliente() {
    }

    public Cliente(Long id, String name, TipoUsuario tipoUsuario, String email, String phone, String dni, List<Carrito> carritos) {
        this.id = id;
        this.name = name;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.phone = phone;
        this.dni = dni;
        this.carritos = carritos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }
}
