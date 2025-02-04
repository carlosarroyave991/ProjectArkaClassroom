package com.arka.classroom.arka.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Entity(name = "categorias")
@Table()
public class Category {
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

    public Category(Long id, String name, String description, String image, Date activeSince) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.activeSince = activeSince;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Date activeSince) {
        this.activeSince = activeSince;
    }
}
