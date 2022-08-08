package com.Motorcycle.MotorcycleStar.dtos.Customer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpsertDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String phone;

}
