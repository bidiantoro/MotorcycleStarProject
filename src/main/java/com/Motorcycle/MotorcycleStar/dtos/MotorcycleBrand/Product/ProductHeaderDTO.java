package com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.Product;

import com.Motorcycle.MotorcycleStar.models.MotorcycleBrand;
import com.Motorcycle.MotorcycleStar.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductHeaderDTO {
    private String id;
    private String brandID;
    private String vehicleModel;
    private String vehicleType;
    private String productionYear;
    private String engine;
    private String unitPrice;

    public ProductHeaderDTO(String id, MotorcycleBrand brandID, String vehicleModel, String vehicleType, String productionYear, String engine, String unitPrice) {
        this.id = id;
        this.brandID = brandID.getId();
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.productionYear = productionYear;
        this.engine = engine;
        this.unitPrice = unitPrice;
    }


    public static ProductHeaderDTO set(Product product) {
        NumberFormat formatMoney = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return new ProductHeaderDTO(
                product.getId(),
                product.getBrandID(),
                product.getVehicleModel(),
                product.getVehicleType(),
                product.getProductionYear(),
                product.getEngine(),
                formatMoney.format(product.getUnitPrice())
        );

    }

    public static List<ProductHeaderDTO> toList(List<Product> products) {
        List<ProductHeaderDTO> result = new ArrayList<>();
        for (Product product : products) {
            result.add(set(product));
        }
        return result;
    }

}
