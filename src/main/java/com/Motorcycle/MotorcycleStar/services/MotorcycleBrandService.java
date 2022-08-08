package com.Motorcycle.MotorcycleStar.services;

import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.MotorcycleBrandDTO;

import java.util.List;

public interface MotorcycleBrandService {

    public List<MotorcycleBrandDTO> findAllMotorcycleBrand();

    public MotorcycleBrandDTO findById(String id);

    public MotorcycleBrandDTO updateBrand(String id, MotorcycleBrandDTO motorcycleBrandDTO);

    public MotorcycleBrandDTO insertBrand(MotorcycleBrandDTO insertBrand);

    public Boolean deleteBrandById(String id);




}
