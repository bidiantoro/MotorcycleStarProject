package com.Motorcycle.MotorcycleStar.controller;


import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.Product.ProductHeaderDTO;
import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.Product.ProductUpsertDTO;
import com.Motorcycle.MotorcycleStar.dtos.RestResponse;
import com.Motorcycle.MotorcycleStar.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    //find all use stream
    @GetMapping
    public ResponseEntity<RestResponse<List<ProductHeaderDTO>>> findAllProduct() {
        return new ResponseEntity<>(
                new RestResponse<>(productService.findAllProduct(),
                        "Product berhasil ditemukan",
                        200),
                HttpStatus.OK);
    }

    //find by id
    @GetMapping("{id}")
    public ResponseEntity<RestResponse<ProductHeaderDTO>> findProductById(@PathVariable("id") String id) {
        return new ResponseEntity<>(
                new RestResponse<>(productService.findProductById(id),
                        "Product berhasil ditemukan",
                        200),
                HttpStatus.OK);
    }

    //update product by id
    @PutMapping("{id}")
    public ResponseEntity<RestResponse<ProductHeaderDTO>> updateProduct(@PathVariable String id, @RequestBody ProductUpsertDTO productUpsertDTO) {
        ProductHeaderDTO product = productService.updateProduct(id, productUpsertDTO);
        return new ResponseEntity<>(
                new RestResponse<>(product,
                        "Product berhasil diupdate",
                        200),
                HttpStatus.OK);
    }

    //insert product
    @PostMapping
    public ResponseEntity<RestResponse<ProductHeaderDTO>> insertProduct(@RequestBody ProductUpsertDTO productUpsertDTO) {
        ProductHeaderDTO product = productService.insertProduct(productUpsertDTO);
        return new ResponseEntity<>(
                new RestResponse<>(product,
                        "Product berhasil ditambahkan",
                        200),
                HttpStatus.OK);
    }

    //delete product
    @DeleteMapping("{id}")
    public ResponseEntity<RestResponse<String>> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(
                new RestResponse<>("Product berhasil dihapus",
                        "Product berhasil dihapus",
                        200),
                HttpStatus.OK);
    }

}
