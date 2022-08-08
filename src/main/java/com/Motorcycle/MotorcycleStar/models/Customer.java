package com.Motorcycle.MotorcycleStar.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID", nullable = false)
    private Integer id;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "Address", nullable = false, length = 500)
    private String address;

    @Column(name = "Gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "Phone", nullable = false, length = 14)
    private String phone;

    @OneToMany(mappedBy = "customerID")
    private Set<Transaction> transaction = new LinkedHashSet<>();

    public String fetchFullName(){
        return String.format("%s %s" , this.firstName , this.lastName == null ? "" :this.lastName );
    }

//    public Customer(Integer id, String firstName, String lastName, String address, String gender, String phone) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.gender = gender;
//        this.phone = phone;
//    }

}