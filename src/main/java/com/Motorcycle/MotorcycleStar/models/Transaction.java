package com.Motorcycle.MotorcycleStar.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"Transaction\"")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customerID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProductID", nullable = false)
    private Product productID;

    @Column(name = "Quantitiy", nullable = false)
    private Integer quantitiy;

    @Column(name = "PurchaseDate", nullable = false)
    private LocalDate purchaseDate;

    public Transaction(Customer customerID, Product productID, Integer quantitiy, LocalDate purchaseDate) {
        this.customerID = customerID;
        this.productID = productID;
        this.quantitiy = quantitiy;
        this.purchaseDate = purchaseDate;
    }
}