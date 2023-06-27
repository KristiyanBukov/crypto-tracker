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
        for (Transaction transaction : transactions) {
            uniqueAssetTypes.add(transaction.getAssetType());
        }

        Map<AssetType, CoinGeckoMarketsItem> apiResponse = new HashMap<>();

        for (AssetType assetType : uniqueAssetTypes) {
            apiResponse.put(assetType, coinGeckoService.getMarketsData(assetType));
        }

        List<TransactionStatisticDto> transactionStatisticDtos = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionStatisticDto transactionDto = new TransactionStatisticDto();
            CoinGeckoMarketsItem coinGeckoMarketsItem = apiResponse.get(transaction.getAssetType());

            setValuesFromEntityToDto(coinGeckoMarketsItem, transaction, transactionDto);

            transactionStatisticDtos.add(transactionDto);
        }

        return transactionStatisticDtos;
    }


    @Override
    public List<TransactionStatisticDto> getTransactionsStatisticsByAssetType(AssetType assetType) {

        List<Transaction> transactions = transactionRepository.findByAssetType(assetType);
        List<TransactionStatisticDto> transactionStatisticDtos = new ArrayList<>();
        CoinGeckoMarketsItem coinGeckoMarketResponse = coinGeckoService.getMarketsData(assetType);


        for (Transaction transaction : transactions) {
            TransactionStatisticDto transactionDto = new TransactionStatisticDto();

            setValuesFromEntityToDto(coinGeckoMarketResponse, transaction, transactionDto);


            transactionStatisticDtos.add(transactionDto);
        }

        return  transactionStatisticDtos;
    }

    private void setValuesFromEntityToDto(CoinGeckoMarketsItem coinGeckoMarketResponse, Transaction transaction, TransactionStatisticDto transactionDto) {
        transactionDto.setId(transaction.getId());
        transactionDto.setAssetType(transaction.getAssetType());
        transactionDto.setBuyDate(transaction.getBuyDate());
        transactionDto.setCurrencyInvested(transaction.getCurrencyInvested());
        transactionDto.setAmountOfAsset(transaction.getAmountOfAsset());
        transactionDto.setAssetBuyingPrice(transaction.getAssetBuyingPrice());
        transactionDto.setCurrentValue(calculateCurrentValue(transactionDto.getAmountOfAsset(),
                coinGeckoMarketResponse.getCurrentPrice()));
        transactionDto.setPriceChange24h(coinGeckoMarketResponse.getPriceChange24H());
        transactionDto.setProfitLoss(calculateProfitLoss(transactionDto.getCurrentValue(),
                transactionDto.getCurrencyInvested()));
        transactionDto.setCurrentPriceOfAsset(coinGeckoMarketResponse.getCurrentPrice());
        transactionDto.setRoi(calculateRoi(transactionDto.getProfitLoss(),
                    transactionDto.getCurrencyInvested()));
        transactionDto.setAverageDailyRoi(calculateAverageRoi(transactionDto.getRoi(),
                    transaction.getBuyDate()));



    }

    @Override
    public List<AssetTypeStatisticDto> getAssetTypesStatistics() {
        return null;
    }

    private BigDecimal calculateCurrentValue(BigDecimal amount, BigDecimal currentPrice) {
        return amount.multiply(currentPrice).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateProfitLoss(BigDecimal currentValue, BigDecimal currencyInvested) {
        return currentValue.subtract(currencyInvested);
    }

    private BigDecimal calculateRoi(BigDecimal profitLoss, BigDecimal currencyInvested) {
        return profitLoss.divide(currencyInvested );
    }

    private BigDecimal calculateAverageRoi(BigDecimal roi, LocalDate buyingDate) {
        return roi.divide(BigDecimal.valueOf(
                ChronoUnit.DAYS.between(buyingDate, LocalDate.now())).add(BigDecimal.valueOf(1)));
    }

}
