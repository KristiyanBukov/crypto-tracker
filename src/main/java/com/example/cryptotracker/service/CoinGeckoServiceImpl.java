package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.dto.coingecko.history.CoinGeckoHistoryItem;
import com.example.cryptotracker.dto.coingecko.market.CoinGeckoMarketsItem;
import com.example.cryptotracker.enums.AssetType;
import com.example.cryptotracker.enums.CurrencyType;
import com.example.cryptotracker.exception.IllegalStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CoinGeckoServiceImpl implements CoinGeckoService {

    private final RestTemplate restTemplate;
    private final static DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    public CoinGeckoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CoinGeckoMarketsItem getMarketsData(AssetType assetType) {

        String url = String.format("https://api.coingecko.com/api/v3/coins/markets?vs_currency=%s&ids=%s"
                , CurrencyType.USD, assetType.getName());

        ParameterizedTypeReference<List<CoinGeckoMarketsItem>> responseType = new ParameterizedTypeReference<>() {
        };
        // Wrapping CoinGeckoMarketsItem into list bec we are getting list from the api response.

        ResponseEntity<List<CoinGeckoMarketsItem>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<CoinGeckoMarketsItem> coinGeckoMarketsItems = responseEntity.getBody();

        if (CollectionUtils.isEmpty(coinGeckoMarketsItems)) {
            throw new IllegalStateException("Failed to retrieve markets information!");
        }

        return coinGeckoMarketsItems.get(0);
    }

    @Override
    public CoinGeckoHistoryItem getHistoryData(TransactionDto transactionDto) {

        String url = String.format("https://api.coingecko.com/api/v3/coins/%s/history?date=%s"
                , transactionDto.getAssetType().getName(), transactionDto.getBuyDate().format(OUTPUT_FORMAT));

        ResponseEntity<CoinGeckoHistoryItem> responseEntity = restTemplate.getForEntity(url, CoinGeckoHistoryItem.class);

        if (responseEntity.getBody() == null) {
            throw new IllegalStateException("Failed to retrieve history information!");
        }
        return responseEntity.getBody();

    }
}
