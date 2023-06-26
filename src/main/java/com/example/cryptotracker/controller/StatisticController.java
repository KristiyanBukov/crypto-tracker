package com.example.cryptotracker.controller;

import com.example.cryptotracker.dto.TransactionStatisticDto;
import com.example.cryptotracker.enums.AssetType;
import com.example.cryptotracker.service.StatisticService;
import com.example.cryptotracker.service.StatisticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticServiceImpl statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public List<TransactionStatisticDto> getTransactionsStatistic(){
        return statisticService.getTransactionsStatistics();
    }

    @GetMapping(path ="/byassettype" )
    public List<TransactionStatisticDto> getTransactionsStatisticByAssetType(AssetType assetType) {
        return statisticService.getTransactionsStatisticsByAssetType(assetType);
    }
}
