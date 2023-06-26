package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionStatisticDto;
import com.example.cryptotracker.dto.coingecko.market.CoinGeckoMarketsItem;
import com.example.cryptotracker.dto.AssetTypeStatisticDto;
import com.example.cryptotracker.enitity.Transaction;
import com.example.cryptotracker.enums.AssetType;
import com.example.cryptotracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class StatisticServiceImpl implements StatisticService {

    private final CoinGeckoServiceImpl coinGeckoService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public StatisticServiceImpl(CoinGeckoServiceImpl coinGeckoService, TransactionRepository transactionRepository) {
        this.coinGeckoService = coinGeckoService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionStatisticDto> getTransactionsStatistics() {
        List<Transaction> transactions = transactionRepository.findAll();

        Set<AssetType> uniqueAssetTypes = new HashSet<>();
        for (Transaction tran : transactions) {
            uniqueAssetTypes.add(tran.getAssetType());
        }

        Map<AssetType, CoinGeckoMarketsItem> apiResponse = new HashMap<>();

        for (AssetType assetType : uniqueAssetTypes) {
            apiResponse.put(assetType, coinGeckoService.getMarketsData(assetType));
        }

        List<TransactionStatisticDto> transactionStatistic = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionStatisticDto newTransactionDto = new TransactionStatisticDto();
            CoinGeckoMarketsItem coinGeckoMarketsItem = apiResponse.get(transaction.getAssetType());

            newTransactionDto.setId(transaction.getId());
            newTransactionDto.setAssetType(transaction.getAssetType());
            newTransactionDto.setBuyDate(transaction.getBuyDate());
            newTransactionDto.setCurrencyInvested(transaction.getCurrencyInvested());
            newTransactionDto.setAmountOfAsset(transaction.getAmountOfAsset());
            newTransactionDto.setAssetBuyingPrice(transaction.getAssetBuyingPrice());
            newTransactionDto.setCurrentValue(calculateCurrentValue(newTransactionDto.getAmountOfAsset(),
                    coinGeckoMarketsItem.getCurrentPrice()));
            newTransactionDto.setPriceChange24h(coinGeckoMarketsItem.getPriceChange24H());
            newTransactionDto.setCurrentPriceOfAsset(coinGeckoMarketsItem.getCurrentPrice());
            newTransactionDto.setProfitLoss(calculateProfitLoss(newTransactionDto.getCurrentValue(),
                    transaction.getCurrencyInvested()));
            newTransactionDto.setRoi(calculateRoi(newTransactionDto.getProfitLoss(),
                    transaction.getCurrencyInvested()));
            newTransactionDto.setAverageDailyRoi(calculateAverageRoi(newTransactionDto.getRoi(),
                    transaction.getBuyDate()));

            transactionStatistic.add(newTransactionDto);
        }

        return transactionStatistic;
    }


    @Override
    public List<TransactionStatisticDto> getTransactionsStatisticsByAssetType(AssetType assetType) {

        List<Transaction> transactionList = transactionRepository.findByAssetType(assetType);
        List<TransactionStatisticDto> transactionStatisticDtos = new ArrayList<>();
        CoinGeckoMarketsItem coinGeckoMarketResponse = coinGeckoService.getMarketsData(assetType);


        for (Transaction transaction : transactionList) {
            TransactionStatisticDto transactionStatisticDto = new TransactionStatisticDto();
            transactionStatisticDto.setId(transaction.getId());
            transactionStatisticDto.setAssetType(transaction.getAssetType());
            transactionStatisticDto.setBuyDate(transaction.getBuyDate());
            transactionStatisticDto.setCurrencyInvested(transaction.getCurrencyInvested());
            transactionStatisticDto.setAmountOfAsset(transaction.getAmountOfAsset());
            transactionStatisticDto.setAssetBuyingPrice(transaction.getAssetBuyingPrice());
            transactionStatisticDto.setCurrentValue(calculateCurrentValue(transactionStatisticDto.getAmountOfAsset(),
                    coinGeckoMarketResponse.getCurrentPrice()));
            transactionStatisticDto.setPriceChange24h(coinGeckoMarketResponse.getPriceChange24H());
            transactionStatisticDto.setProfitLoss(calculateProfitLoss(transactionStatisticDto.getCurrentValue(),
                    transactionStatisticDto.getCurrencyInvested()));
            transactionStatisticDto.setCurrentPriceOfAsset(coinGeckoMarketResponse.getCurrentPrice());
            transactionStatisticDto.setRoi(calculateRoi(transactionStatisticDto.getProfitLoss(),
                    transactionStatisticDto.getCurrencyInvested()));
            transactionStatisticDto.setAverageDailyRoi(calculateAverageRoi(transactionStatisticDto.getRoi(),
                    transaction.getBuyDate()));

            transactionStatisticDtos.add(transactionStatisticDto);
        }

        return  transactionStatisticDtos;
    }

    @Override
    public List<AssetTypeStatisticDto> getAssetTypesStatistics() {
        return null;
    }

    private BigDecimal calculateCurrentValue(BigDecimal amount, BigDecimal currentPrice) {
        return amount.multiply(currentPrice);
    }

    private BigDecimal calculateProfitLoss(BigDecimal currentValue, BigDecimal currencyInvested) {
        return currentValue.subtract(currencyInvested);
    }

    private BigDecimal calculateRoi(BigDecimal profitLoss, BigDecimal currencyInvested) {
        return profitLoss.divide(currencyInvested, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateAverageRoi(BigDecimal roi, LocalDate buyingDate) {
        return roi.divide(BigDecimal.valueOf(
                ChronoUnit.DAYS.between(buyingDate, LocalDate.now())).add(BigDecimal.valueOf(1)), RoundingMode.HALF_UP);
    }

}
