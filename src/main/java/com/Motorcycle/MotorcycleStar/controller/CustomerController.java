package com.Motorcycle.MotorcycleStar.controller;

import com.Motorcycle.MotorcycleStar.dtos.Customer.CustomerHeaderDTO;
import com.Motorcycle.MotorcycleStar.dtos.Customer.CustomerUpsertDTO;
import com.Motorcycle.MotorcycleStar.dtos.RestResponse;
import com.Motorcycle.MotorcycleStar.services.CustomerService;
import com.Motorcycle.MotorcycleStar.services.CustomerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //find all customer
    @GetMapping
    public ResponseEntity<RestResponse<List<CustomerHeaderDTO>>> findAllCustomer() {
        return new ResponseEntity<>(
                new RestResponse<>(customerService.findAllCustomer(),
                        "Customer berhasil ditemukan",
                        200),
                HttpStatus.OK);
    }

    //find by id
    @GetMapping("{id}")
    public ResponseEntity<RestResponse<CustomerHeaderDTO>> findByIdCustomer(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
                new RestResponse<>(customerService.findCustomerById(id),
                        "Customer berhasil ditemukan",
                        200),
                HttpStatus.OK);
    }

    //update customer by id
    @PutMapping("{id}")
    public ResponseEntity<RestResponse<CustomerHeaderDTO>> updateCustomer(@PathVariable Integer id , @RequestBody CustomerUpsertDTO customerUpsertDTO) {
        CustomerHeaderDTO customer = customerService.updateCustomer(id, customerUpsertDTO);
        return new ResponseEntity<>(
                new RestResponse<>(customer,
                        "Customer berhasil diupdate",
                        200),
                HttpStatus.OK);
    }

    //insert customer
    @PostMapping
    public ResponseEntity<RestResponse<CustomerHeaderDTO>> insertCustomer(@RequestBody CustomerUpsertDTO newCustomer) {
        return new ResponseEntity<>(
                new RestResponse<>(customerService.insertCustomer(newCustomer),
                        "Customer berhasil ditambahkan",
                        201),
                HttpStatus.CREATED);
    }

    //delete customer
    @DeleteMapping("{id}")
    public ResponseEntity<RestResponse<Boolean>> deleteCustomerById(@PathVariable Integer id) {
        return new ResponseEntity<>(
                new RestResponse<>(customerService.deleteCustomerById(id),
                        "Customer berhasil dihapus",
                        200),
                HttpStatus.OK);
    }






}
