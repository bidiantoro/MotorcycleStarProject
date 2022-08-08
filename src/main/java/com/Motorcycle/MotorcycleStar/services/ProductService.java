package com.Motorcycle.MotorcycleStar.services;

import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.Product.ProductHeaderDTO;
import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.Product.ProductUpsertDTO;

import java.util.List;

public interface ProductService {

    public List<ProductHeaderDTO> findAllProduct();

    public ProductHeaderDTO findProductById(String id);

    public ProductHeaderDTO updateProduct(String id, ProductUpsertDTO productUpsertDTO);

    public ProductHeaderDTO insertProduct(ProductUpsertDTO productUpsertDTO);

    public boolean deleteProduct(String id);
}
