package com.example.cryptotracker.service;

import com.example.cryptotracker.enums.AssetType;
import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.enitity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(TransactionDto transactionDto);
    Transaction updateTransaction(Long id, TransactionDto transactionDto);
    Transaction getTransaction(Long id);
    List<Transaction> getAll(AssetType assetType);
    void deleteTransaction(Long id);
}
