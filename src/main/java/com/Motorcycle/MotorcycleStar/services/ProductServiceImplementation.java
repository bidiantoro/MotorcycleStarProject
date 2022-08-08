package com.Motorcycle.MotorcycleStar.services;

import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.Product.ProductHeaderDTO;

import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.Product.ProductUpsertDTO;
import com.Motorcycle.MotorcycleStar.models.MotorcycleBrand;
import com.Motorcycle.MotorcycleStar.models.Product;
import com.Motorcycle.MotorcycleStar.repositories.MotorcycleBrandRepository;
import com.Motorcycle.MotorcycleStar.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {


    private ProductRepository productRepository;
    private MotorcycleBrandRepository motorcycleBrandRepository;

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepository, MotorcycleBrandRepository motorcycleBrandRepository) {
        this.productRepository = productRepository;
        this.motorcycleBrandRepository = motorcycleBrandRepository;
    }


    //find all product use stream
    @Override
    public List<ProductHeaderDTO> findAllProduct() {
        return productRepository.findAll().stream()
                .map(ProductHeaderDTO::set)
                .collect(Collectors.toList());
    }

    //find by id
    @Override
    public ProductHeaderDTO findProductById(String id) {
        return productRepository.findById(id)
                .map(ProductHeaderDTO::set)
                .orElseThrow(() -> new EntityNotFoundException("Product dengan id " + id + " tidak ditemukan"));
    }

    //update product by id
    @Override
    public ProductHeaderDTO updateProduct(String id, ProductUpsertDTO productUpsertDTO) {


        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        MotorcycleBrand motorcycleBrand;

        if(productUpsertDTO.getBrandID() != null){
             motorcycleBrand = motorcycleBrandRepository.findById(productUpsertDTO.getBrandID())
                    .orElseThrow(() -> new EntityNotFoundException("Motorcycle Brand not found"));

        } else {
            motorcycleBrand = oldProduct.getBrandID();
        }

        oldProduct.setBrandID(motorcycleBrand);
        oldProduct.setVehicleModel(productUpsertDTO.getVehicleModel() == null? oldProduct.getVehicleModel() : productUpsertDTO.getVehicleModel());
        oldProduct.setVehicleType(productUpsertDTO.getVehicleType() == null? oldProduct.getVehicleType() : productUpsertDTO.getVehicleType());
        oldProduct.setProductionYear(productUpsertDTO.getProductionYear() == null? oldProduct.getProductionYear() : productUpsertDTO.getProductionYear());
        oldProduct.setEngine(productUpsertDTO.getEngine() == null? oldProduct.getEngine() : productUpsertDTO.getEngine());
        oldProduct.setUnitPrice(productUpsertDTO.getUnitPrice() == null? oldProduct.getUnitPrice() : productUpsertDTO.getUnitPrice());

        productRepository.save(oldProduct);
        return ProductHeaderDTO.set(oldProduct);
    }

    //insert product
    @Override
    public ProductHeaderDTO insertProduct(ProductUpsertDTO productUpsertDTO) {
        Product product = new Product();
        MotorcycleBrand motorcycleBrand;

        if(productUpsertDTO.getBrandID() != null){
            motorcycleBrand = motorcycleBrandRepository.findById(productUpsertDTO.getBrandID())
                    .orElseThrow(() -> new EntityNotFoundException("Motorcycle Brand not found"));

        } else {
            motorcycleBrand = new MotorcycleBrand();
        }
        product.setId(productUpsertDTO.getId());
        product.setBrandID(motorcycleBrand);
        product.setVehicleModel(productUpsertDTO.getVehicleModel());
        product.setVehicleType(productUpsertDTO.getVehicleType());
        product.setProductionYear(productUpsertDTO.getProductionYear());
        product.setEngine(productUpsertDTO.getEngine());
        product.setUnitPrice(productUpsertDTO.getUnitPrice());

        productRepository.save(product);
        return ProductHeaderDTO.set(product);
    }

    //delete product
    @Override
    public boolean deleteProduct(String id) {
        productRepository.findById(id)
                .orElseThrow((()-> new RuntimeException("ProductID not found")));
        productRepository.deleteById(id);

        return true;
    }



}
