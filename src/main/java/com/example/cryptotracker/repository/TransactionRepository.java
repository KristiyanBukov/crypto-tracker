package com.example.cryptotracker.repository;

import com.example.cryptotracker.enitity.Transaction;
import com.example.cryptotracker.enums.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAssetType(AssetType assetType);
}
