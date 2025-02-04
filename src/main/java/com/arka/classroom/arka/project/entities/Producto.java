package com.arka.classroom.arka.project.entities;

import jakarta.persistence.*;

@Entity(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto")
    private String name;

    @Column(name = "marca")
    private String stamp;

    @Column(name = "categoria")
    private String category;

    @Column(name = "precio")
    private Float price;

    @Column()
    private Integer stock;

    public Producto() {
    }

    public Producto(String name, String stamp, String category, Float price, Integer stock) {
        this.name = name;
        this.stamp = stamp;
        this.category = category;
        this.price = price;
        this.stock = stock;
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

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
