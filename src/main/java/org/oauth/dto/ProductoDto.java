package org.oauth.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.oauth.enums.Color;
import lombok.Data;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {
    private String id;
    private String nombre;
    private double precio;
    private boolean enStock;
    private Set<Color> colores;
}
