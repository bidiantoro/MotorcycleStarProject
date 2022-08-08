package com.Motorcycle.MotorcycleStar.dtos.Customer;

import com.Motorcycle.MotorcycleStar.models.Customer;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class CustomerHeaderDTO{
    private Integer id;
    private String fullName;
    private String address;
    private String gender;
    private String phone;

    public CustomerHeaderDTO(Integer id, String fullName, String address, String gender, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
    }

    public static CustomerHeaderDTO set(Customer customer) {
        return new CustomerHeaderDTO(
                customer.getId(),
                customer.fetchFullName(),
                customer.getAddress(),
                customer.getGender(),
                customer.getPhone()
        );
    }

    public static List<CustomerHeaderDTO> toList(List<Customer> customers) {
        List<CustomerHeaderDTO> result = new ArrayList<>();
        for (Customer customer : customers) {
            result.add(set(customer));
        }
        return result;
    }
}

