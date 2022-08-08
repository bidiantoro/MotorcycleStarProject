package com.Motorcycle.MotorcycleStar.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MotorcycleBrand {
    @Id
    @Column(name = "BrandID", nullable = false, length = 10)
    private String id;

    @Column(name = "Company", nullable = false, length = 50)
    private String company;

    @Column(name = "Country", nullable = false, length = 50)
    private String country;

    @OneToMany(mappedBy = "brandID")
    private Set<Product> products = new LinkedHashSet<>();

}