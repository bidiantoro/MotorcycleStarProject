package com.Motorcycle.MotorcycleStar.repositories;

import com.Motorcycle.MotorcycleStar.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}