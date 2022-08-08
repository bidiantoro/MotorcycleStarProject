package com.Motorcycle.MotorcycleStar.services;

import com.Motorcycle.MotorcycleStar.dtos.Customer.CustomerHeaderDTO;
import com.Motorcycle.MotorcycleStar.dtos.Customer.CustomerUpsertDTO;
import com.Motorcycle.MotorcycleStar.models.Customer;
import com.Motorcycle.MotorcycleStar.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplementation implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    //find all customer stream
    @Override
    public List<CustomerHeaderDTO> findAllCustomer() {
        return customerRepository.findAll().stream()
                .map(CustomerHeaderDTO::set)
                .collect(Collectors.toList());
    }

    //find by id
    @Override
    public CustomerHeaderDTO findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .map(CustomerHeaderDTO::set)
                .orElseThrow(() -> new EntityNotFoundException("Customer dengan id " + id + " tidak ditemukan"));
    }

    //update customer by id
    @Override
    public CustomerHeaderDTO updateCustomer(Integer id, CustomerUpsertDTO customerUpsertDTO) {
        Customer oldCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        oldCustomer.setFirstName(customerUpsertDTO.getFirstName() == null ? oldCustomer.getFirstName() : customerUpsertDTO.getFirstName());
        oldCustomer.setLastName(customerUpsertDTO.getLastName() == null? oldCustomer.getLastName() : customerUpsertDTO.getLastName());
        oldCustomer.setAddress(customerUpsertDTO.getAddress() == null? oldCustomer.getAddress() : customerUpsertDTO.getAddress());
        oldCustomer.setGender(customerUpsertDTO.getGender() == null? oldCustomer.getGender() : customerUpsertDTO.getGender());
        oldCustomer.setPhone(customerUpsertDTO.getPhone() == null? oldCustomer.getPhone() : customerUpsertDTO.getPhone());
        customerRepository.save(oldCustomer);
        return CustomerHeaderDTO.set(oldCustomer);
    }

    //insert customer
    @Override
    public CustomerHeaderDTO insertCustomer(CustomerUpsertDTO customerUpsertDTO) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerUpsertDTO.getFirstName());
        newCustomer.setLastName(customerUpsertDTO.getLastName());
        newCustomer.setAddress(customerUpsertDTO.getAddress());
        newCustomer.setGender(customerUpsertDTO.getGender());
        newCustomer.setPhone(customerUpsertDTO.getPhone());

        customerRepository.save(newCustomer);
        return CustomerHeaderDTO.set(newCustomer);
    }

    //delete by id
    @Override
    public boolean deleteCustomerById(Integer id) {
        customerRepository.findById(id)
                .orElseThrow((() -> new EntityNotFoundException("Customer dengan id " + id + " tidak ditemukan")));
        customerRepository.deleteById(id);
        return true;
    }



}
