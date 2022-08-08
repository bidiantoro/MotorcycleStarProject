package com.Motorcycle.MotorcycleStar.services;

import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.MotorcycleBrandDTO;
import com.Motorcycle.MotorcycleStar.models.MotorcycleBrand;
import com.Motorcycle.MotorcycleStar.repositories.MotorcycleBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotorcycleBrandImplementation implements MotorcycleBrandService {


    private  MotorcycleBrandRepository motorcycleBrandRepository;

    @Autowired
    public MotorcycleBrandImplementation(MotorcycleBrandRepository motorcycleBrandRepository) {
        this.motorcycleBrandRepository = motorcycleBrandRepository;
    }

    //find all motorcycle brand stream
    @Override
    public List<MotorcycleBrandDTO> findAllMotorcycleBrand() {
        return motorcycleBrandRepository.findAll().stream()
                .map(MotorcycleBrandDTO::set)
                .collect(Collectors.toList());
    }

    //find by id motorcycle
    @Override
    public MotorcycleBrandDTO findById(String id) {
        return motorcycleBrandRepository.findById(id).map(MotorcycleBrandDTO::set)
                .orElseThrow(() -> new RuntimeException("Motorcycle brand id tidak ditemukan"));
    }

    //update brand by id
    @Override
    public MotorcycleBrandDTO updateBrand(String id, MotorcycleBrandDTO motorcycleBrandDTO) {
        return motorcycleBrandRepository.findById(id).map(brand -> {
            brand.setCompany(motorcycleBrandDTO.getCompany() == null ? brand.getCompany() : motorcycleBrandDTO.getCompany());
            brand.setCountry(motorcycleBrandDTO.getCountry() == null ? brand.getCountry() : motorcycleBrandDTO.getCountry());
            return motorcycleBrandRepository.save(brand);
        }).map(MotorcycleBrandDTO::set)
                .orElseThrow(() -> new RuntimeException("Motorcycle brand tidak ditemukan"));
    }

    //insert brand
    @Override
    public MotorcycleBrandDTO insertBrand(MotorcycleBrandDTO insertBrand) {
        MotorcycleBrand newBrand = new MotorcycleBrand();
        newBrand.setId(insertBrand.getId());
        newBrand.setCompany(insertBrand.getCompany());
        newBrand.setCountry(insertBrand.getCountry());

        motorcycleBrandRepository.save(newBrand);
        return MotorcycleBrandDTO.set(newBrand);
    }

    //delete brand
    @Override
    public Boolean deleteBrandById(String id) {
        motorcycleBrandRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Motorcycle brand tidak ditemukan"));
        motorcycleBrandRepository.deleteById(id);
        return true;
    }

}
