package com.arka.classroom.arka.project.models.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;


public class CreateCategoryDto {
    @NotBlank()
    String nombre;

    @NotBlank()
    String descripcion;

    @NotBlank()
    String imagen;

    @NotBlank()
    Date activeSince;

    //Constructor
    public CreateCategoryDto(String nombre, String descripcion, String imagen, Date activeSince) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.activeSince = activeSince;
    }

    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Date activeSince) {
        this.activeSince = activeSince;
    }

    public String getDescription() {
        return this.descripcion;
    }
}
