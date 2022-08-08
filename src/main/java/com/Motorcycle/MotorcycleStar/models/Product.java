package com.Motorcycle.MotorcycleStar.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @Column(name = "ProductID", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BrandID", nullable = false)
    private MotorcycleBrand brandID;

    @Column(name = "VehicleModel", nullable = false, length = 50)
    private String vehicleModel;

    @Column(name = "VehicleType", nullable = false, length = 50)
    private String vehicleType;

    @Column(name = "ProductionYear", nullable = false, length = 4)
    private String productionYear;

    @Column(name = "Engine", nullable = false, length = 7)
    private String engine;

    @Column(name = "UnitPrice", nullable = false, precision = 19, scale = 4)
    private BigDecimal unitPrice;

    @OneToMany(mappedBy = "productID")
    private Set<Transaction> transaction = new LinkedHashSet<>();

}