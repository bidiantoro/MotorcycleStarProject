package com.Motorcycle.MotorcycleStar.repositories;

import com.Motorcycle.MotorcycleStar.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}