package com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand;

import com.Motorcycle.MotorcycleStar.models.MotorcycleBrand;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class MotorcycleBrandDTO {
    public String id;
    public String company;
    public String country;

    public MotorcycleBrandDTO(String id, String company, String country) {
        this.id = id;
        this.company = company;
        this.country = country;
    }

    public static MotorcycleBrandDTO set(MotorcycleBrand motorcycleBrand) {
        return new MotorcycleBrandDTO(
                  motorcycleBrand.getId(),
                    motorcycleBrand.getCompany(),
                    motorcycleBrand.getCountry()
            );

    }

    public static List<MotorcycleBrandDTO> toList(List<MotorcycleBrand> motorcycleBrands) {
        List<MotorcycleBrandDTO> result = new ArrayList<>();
        for (MotorcycleBrand motorcycleBrand : motorcycleBrands) {
            result.add(set(motorcycleBrand));
        }
        return result;
    }

}
