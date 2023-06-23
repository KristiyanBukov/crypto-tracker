package com.example.cryptotracker.controller;

import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.enums.AssetType;
import com.example.cryptotracker.mapper.TransactionMapper;
import com.example.cryptotracker.service.TransactionService;
import com.example.cryptotracker.service.TransactionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/transaction")
@Validated // Validating restrictions in DTO class
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping
    public TransactionDto createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return transactionMapper.toDto(transactionService.createTransaction(transactionDto));
    }

    @PutMapping("/{id}")
    public TransactionDto updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        return transactionMapper.toDto(transactionService.updateTransaction(id, transactionDto));
    }

    @GetMapping("/{id}")
    public TransactionDto getTransaction(@PathVariable Long id) {
        return transactionMapper.toDto(transactionService.getTransaction(id));
    }

    @GetMapping
    public List<TransactionDto> getAll(@RequestParam(required = false) AssetType assetType) {
        return transactionService.getAll(assetType).stream().map(transactionMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
