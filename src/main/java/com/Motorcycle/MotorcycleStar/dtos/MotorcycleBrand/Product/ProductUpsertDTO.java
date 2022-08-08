package com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.Product;

import com.Motorcycle.MotorcycleStar.models.MotorcycleBrand;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpsertDTO {
    private String id;
    private String brandID;
    private String vehicleModel;
    private String vehicleType;
    private String productionYear;
    private String engine;
    private BigDecimal unitPrice;

}

