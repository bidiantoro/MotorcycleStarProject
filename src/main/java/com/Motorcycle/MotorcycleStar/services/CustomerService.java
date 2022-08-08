package com.Motorcycle.MotorcycleStar.services;

import com.Motorcycle.MotorcycleStar.dtos.Customer.CustomerHeaderDTO;
import com.Motorcycle.MotorcycleStar.dtos.Customer.CustomerUpsertDTO;

import java.util.List;

public interface CustomerService {

    public List<CustomerHeaderDTO> findAllCustomer();

    public CustomerHeaderDTO findCustomerById(Integer id);

    public CustomerHeaderDTO updateCustomer(Integer id, CustomerUpsertDTO customerUpsertDTO);

    public CustomerHeaderDTO insertCustomer(CustomerUpsertDTO customerUpsertDTO);


    public boolean deleteCustomerById(Integer id);
}
