package com.Motorcycle.MotorcycleStar.services;

import com.Motorcycle.MotorcycleStar.dtos.Transaction.TransactionGridDTO;
import com.Motorcycle.MotorcycleStar.dtos.Transaction.TransactionUpsertDTO;

import java.util.List;

public interface TransactionService {

    public List<TransactionGridDTO> findAllTransaction();

    public List<TransactionGridDTO> findTransactionById(Integer id);

    public List<TransactionGridDTO> findTransactionByCustomerName(String customerName);

    public TransactionGridDTO insertTransaction(TransactionUpsertDTO insertTranscation);

    public TransactionGridDTO updateTransaction(Integer id, TransactionUpsertDTO updateTransactionDTO);

    public Boolean deleteTransaction(Integer id);


}
