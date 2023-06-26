package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionStatisticDto;
import com.example.cryptotracker.dto.AssetTypeStatisticDto;
import com.example.cryptotracker.enums.AssetType;

import java.util.List;

public interface StatisticService {

    List<TransactionStatisticDto> getTransactionsStatistics();

    List<TransactionStatisticDto> getTransactionsStatisticsByAssetType(AssetType AssetType);
    List<AssetTypeStatisticDto> getAssetTypesStatistics();


}
