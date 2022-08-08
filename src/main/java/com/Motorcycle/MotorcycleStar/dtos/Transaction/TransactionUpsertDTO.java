package com.Motorcycle.MotorcycleStar.dtos.Transaction;

import com.Motorcycle.MotorcycleStar.models.Customer;
import com.Motorcycle.MotorcycleStar.models.Product;
import com.Motorcycle.MotorcycleStar.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Optional;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TransactionUpsertDTO {
    private Integer id;
    private Integer customerID;
    private String productID;
    private Integer quantitiy;
    private String purchaseDate;


    public Transaction toEntity(Optional<Customer> customer, Optional<Product> product) {
        return new Transaction(
                customer.get(),
                product.get(),
                quantitiy,
                LocalDate.now()
        );
    }

}
