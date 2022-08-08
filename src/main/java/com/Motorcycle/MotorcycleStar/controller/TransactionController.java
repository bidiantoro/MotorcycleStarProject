package com.Motorcycle.MotorcycleStar.controller;

import com.Motorcycle.MotorcycleStar.dtos.RestResponse;
import com.Motorcycle.MotorcycleStar.dtos.Transaction.TransactionGridDTO;
import com.Motorcycle.MotorcycleStar.dtos.Transaction.TransactionUpsertDTO;
import com.Motorcycle.MotorcycleStar.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //find all transaction
    @GetMapping
    public ResponseEntity<RestResponse<List<TransactionGridDTO>>> findAllTransaction() {
        return new ResponseEntity<>(
                new RestResponse<>(transactionService.findAllTransaction(),
                        "Transaction berhasil ditampilkan",
                        200),
                HttpStatus.OK);
    }

    //find all transaction by customer name
    @GetMapping("{customerName}")
    public ResponseEntity<RestResponse<List<TransactionGridDTO>>> findAllTransactionByCustomerName(@PathVariable String customerName) {
        return new ResponseEntity<>(
                new RestResponse<>(transactionService.findTransactionByCustomerName(customerName),
                        "Transaction berhasil ditemukan",
                        200),
                HttpStatus.OK);
    }

    //insert transaction
    @PostMapping
    public ResponseEntity<RestResponse<TransactionGridDTO>> insertTransaction(@RequestBody TransactionUpsertDTO insertTransaction) {
        return new ResponseEntity<>(
                new RestResponse<>(transactionService.insertTransaction(insertTransaction),
                        "Transaction berhasil ditambahkan",
                        201),
                HttpStatus.CREATED);
    }

    //find by id
    @GetMapping("find/{id}")
    public ResponseEntity<RestResponse<List<TransactionGridDTO>>> findAllTransactionByCustomerName(@PathVariable Integer id) {
        return new ResponseEntity<>(
                new RestResponse<>(transactionService.findTransactionById(id),
                        "Transaction berhasil ditemukan",
                        200),
                HttpStatus.OK);
    }

    //update by id
    @PutMapping("update/{id}")
    public ResponseEntity<RestResponse<TransactionGridDTO>> updateTransaction(@PathVariable Integer id, @RequestBody TransactionUpsertDTO updateTransaction) {
        return new ResponseEntity<>(
                new RestResponse<>(transactionService.updateTransaction(id, updateTransaction),
                        "Transaction berhasil diupdate",
                        200),
                HttpStatus.OK);
    }

    //delete by id
    @DeleteMapping("{id}")
    public ResponseEntity<RestResponse<Boolean>> deleteTransaction(@PathVariable Integer id) {
        return new ResponseEntity<>(
                new RestResponse<>(transactionService.deleteTransaction(id),
                        "Transaction berhasil dihapus",
                        200),
                HttpStatus.OK);
    }

}
