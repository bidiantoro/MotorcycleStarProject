package com.Motorcycle.MotorcycleStar.services;



import com.Motorcycle.MotorcycleStar.dtos.Transaction.TransactionGridDTO;
import com.Motorcycle.MotorcycleStar.dtos.Transaction.TransactionUpsertDTO;
import com.Motorcycle.MotorcycleStar.models.Customer;
import com.Motorcycle.MotorcycleStar.models.Product;
import com.Motorcycle.MotorcycleStar.models.Transaction;
import com.Motorcycle.MotorcycleStar.repositories.CustomerRepository;
import com.Motorcycle.MotorcycleStar.repositories.ProductRepository;
import com.Motorcycle.MotorcycleStar.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImplementation implements TransactionService {


    private TransactionRepository transactionRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;


    @Autowired
    public TransactionServiceImplementation(TransactionRepository transactionRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    //find all transaction
    @Override
    public List<TransactionGridDTO> findAllTransaction() {
        List<Object[]> transactionHeaderDtos = transactionRepository.findAllTransaction();
        List<TransactionGridDTO> transactionGridDTOList = TransactionGridDTO.convert(transactionHeaderDtos);
        return transactionGridDTOList;

    }

    //find transaction by id
    @Override
    public List<TransactionGridDTO> findTransactionById(Integer id) {
        List<Object[]> transaction = transactionRepository.findTransactionById(id);
        if (transaction.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction ID tidak ditemukan");
        }
        List<TransactionGridDTO> transactionGridDTO = TransactionGridDTO.convert(transaction);
        return transactionGridDTO;
    }

    //find transaction by customer name
    @Override
    public List<TransactionGridDTO> findTransactionByCustomerName(String customerName) {
        List<Object[]> transactionHeaderDtos = transactionRepository.findTransactionByCustomerName(customerName);
        if (transactionHeaderDtos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer name tidak ditemukan");
        }
        List<TransactionGridDTO> transactionGridDTOList = TransactionGridDTO.convert(transactionHeaderDtos);
        return transactionGridDTOList;
    }

    //insert transaction
    @Override
    public TransactionGridDTO insertTransaction(TransactionUpsertDTO insertTransaction) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(insertTransaction.getCustomerID())
                .orElseThrow((() -> new RuntimeException("Customer id not found"))));
        Optional<Product> product = Optional.ofNullable(productRepository.findById(insertTransaction.getProductID())
                .orElseThrow((() -> new RuntimeException("Product id not found"))));
        Transaction transaction = insertTransaction.toEntity(customer, product);

        transactionRepository.save(transaction);
        return TransactionGridDTO.set(transaction);

    }


    //update transaction by id
    @Override
    public TransactionGridDTO updateTransaction(Integer id, TransactionUpsertDTO updateTransactionDTO) {
        Transaction oldTransaction = transactionRepository.findById(id).orElseThrow((() -> new RuntimeException("Transaction id tidak ditemukan")));
        if (updateTransactionDTO.getCustomerID() != null) {
            Customer customer = customerRepository.findById(updateTransactionDTO.getCustomerID())
                    .orElseThrow((() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer id tidak ditemukan")));
            oldTransaction.setCustomerID(customer);
        } else {
            oldTransaction.setCustomerID(oldTransaction.getCustomerID());
        }

        if (updateTransactionDTO.getProductID() != null) {
            Product product = productRepository.findById(updateTransactionDTO.getProductID())
                    .orElseThrow((() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product id not found")));
            oldTransaction.setProductID(product);
        } else {
            oldTransaction.setProductID(oldTransaction.getProductID());
        }

        oldTransaction.setQuantitiy(updateTransactionDTO.getQuantitiy() == null ? oldTransaction.getQuantitiy() : updateTransactionDTO.getQuantitiy());
        transactionRepository.save(oldTransaction);
        return TransactionGridDTO.set(oldTransaction);
    }

    //delete transaction by id
    @Override
    public Boolean deleteTransaction(Integer id) {
        Optional<Transaction> transaction = Optional.ofNullable(transactionRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Transaction id not found"))));
        transactionRepository.delete(transaction.get());

        return true;
    }


}




