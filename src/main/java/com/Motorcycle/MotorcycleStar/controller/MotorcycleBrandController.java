package com.Motorcycle.MotorcycleStar.controller;

import com.Motorcycle.MotorcycleStar.dtos.MotorcycleBrand.MotorcycleBrandDTO;
import com.Motorcycle.MotorcycleStar.dtos.RestResponse;
import com.Motorcycle.MotorcycleStar.services.MotorcycleBrandImplementation;
import com.Motorcycle.MotorcycleStar.services.MotorcycleBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class MotorcycleBrandController {

    @Autowired
    private MotorcycleBrandService service;

    //find all motorcycle bran
    @GetMapping
    public ResponseEntity<RestResponse<List<MotorcycleBrandDTO>>> findAllMotorcycleBrand() {
        return new ResponseEntity<>(
                new RestResponse<>(service.findAllMotorcycleBrand(),
                        "Motorcycle brand berhasil ditemukan",
                        200),
                HttpStatus.OK);
    }

    //find by id motorcycle brand
    @GetMapping("{id}")
    public ResponseEntity<RestResponse<MotorcycleBrandDTO>> findById(@PathVariable("id") String id) {
        return new ResponseEntity<>(
                new RestResponse<>(service.findById(id),
                        "Motorcycle brand berhasil ditemukan",
                        200),
                HttpStatus.OK);
    }

    //update brand by id
    @PutMapping("{id}")
    public ResponseEntity<RestResponse<MotorcycleBrandDTO>> updateBrand(@PathVariable String id , @RequestBody MotorcycleBrandDTO brand) {
        MotorcycleBrandDTO brandUpdate = service.updateBrand(id, brand);
        return new ResponseEntity<>(
                new RestResponse<>(brandUpdate,
                        "Motorcycle brand berhasil diupdate",
                        200),
                HttpStatus.OK);
    }

    //insert brand
    @PostMapping
    public ResponseEntity<RestResponse<MotorcycleBrandDTO>> insertBrand(@RequestBody MotorcycleBrandDTO brand) {
        MotorcycleBrandDTO brandInsert = service.insertBrand(brand);
        return new ResponseEntity<>(
                new RestResponse<>(brandInsert,
                        "Motorcycle brand berhasil ditambahkan",
                        200),
                HttpStatus.OK);
    }

    //delete brand
    @DeleteMapping("{id}")
    public ResponseEntity<RestResponse<Boolean>> deleteBrandById(@PathVariable String id) {
        return new ResponseEntity<>(
                new RestResponse<>(service.deleteBrandById(id),
                        "Motorcycle brand berhasil dihapus",
                        200),
                HttpStatus.OK);
    }

}
