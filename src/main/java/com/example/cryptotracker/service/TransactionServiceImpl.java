package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.dto.coingecko.CoinGeckoPrice;
import com.example.cryptotracker.enitity.Transaction;
import com.example.cryptotracker.enums.AssetType;
import com.example.cryptotracker.enums.CurrencyType;
import com.example.cryptotracker.exception.BadRequestException;
import com.example.cryptotracker.mapper.TransactionMapper;
import com.example.cryptotracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CoinGeckoService coinGeckoService;
    private final ExchangeRateService exchangeRateService;
    private static final Set<CurrencyType> SUPPORTED_CURRENCIES = Set.of(CurrencyType.USD, CurrencyType.EUR);

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
                                  CoinGeckoServiceImpl coinGeckoService, ExchangeRateServiceImpl exchangeRateService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.coinGeckoService = coinGeckoService;
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        validateCurrency(transactionDto.getCurrencyType());

        CoinGeckoPrice coinGeckoPrice = transactionDto.getBuyDate().equals(LocalDate.now())
                ? coinGeckoService.getMarketsData(transactionDto.getAssetType())
                : coinGeckoService.getHistoryData(transactionDto);

        if (!transactionDto.getCurrencyType().equals(CurrencyType.USD)) {
            exchangeRateService.getHistoricalExchangeRates(transactionDto);
        }

        if (transactionDto.getAssetBuyingPrice() == null) {
            transactionDto.setAssetBuyingPrice(coinGeckoPrice.getPrice());
            transactionDto.setAmountOfAsset(transactionDto.getCurrencyInvested().divide(
                    coinGeckoPrice.getPrice(),8, RoundingMode.HALF_EVEN));
        }

        transactionDto.setAmountOfAsset(transactionDto.getCurrencyInvested().divide(
                transactionDto.getAssetBuyingPrice(),8, RoundingMode.HALF_EVEN));

        exchangeRateService.getHistoricalRatesUsdEurToBgn(transactionDto);

        return transactionRepository.save(transactionMapper.toEntity(transactionDto));
    }


    private void validateCurrency(CurrencyType currencyType) {
        if (!SUPPORTED_CURRENCIES.contains(currencyType)) {
            throw new BadRequestException("Not supported currency type. We support the following currencies: " + SUPPORTED_CURRENCIES);
        }
    }

    @Override
    public Transaction updateTransaction(Long id, TransactionDto transactionDto) {
        Transaction transaction = getTransaction(id);
        return transactionRepository.save(transactionMapper.partialUpdate(transactionDto, transaction));
    }

    @Override
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new BadRequestException("Couldn't find transaction with id: " + id));
    }

    @Override
    public List<Transaction> getAll(AssetType assetType) {
        return assetType != null ? transactionRepository.findByAssetType(assetType) : transactionRepository.findAll();
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = getTransaction(id);
        transactionRepository.delete(transaction);
    }
}
