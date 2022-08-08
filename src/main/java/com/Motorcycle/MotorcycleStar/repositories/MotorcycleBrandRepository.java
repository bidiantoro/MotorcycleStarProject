package com.Motorcycle.MotorcycleStar.repositories;

import com.Motorcycle.MotorcycleStar.models.MotorcycleBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorcycleBrandRepository extends JpaRepository<MotorcycleBrand, String> {
}