package com.arka.classroom.arka.project.Aplication.models.dto.savedcarrito;

import com.arka.classroom.arka.project.Aplication.models.dto.CreateCarritoDto;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateProductoDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoProductoRequest {
    Long id;

    Integer amount;

    Long carritoId;

    CreateProductoDto productos;
}
