package com.arka.classroom.arka.project.models.dto;

import com.arka.classroom.arka.project.entities.enums.Country;
import jakarta.validation.constraints.NotBlank;


public class CreateMarcaDto {
    @NotBlank()
    private String name;

    @NotBlank()
    private String description;

    @NotBlank()
    private String logo;

    @NotBlank()
    private Country country;

    public CreateMarcaDto(String name, String description, String logo, Country country) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
