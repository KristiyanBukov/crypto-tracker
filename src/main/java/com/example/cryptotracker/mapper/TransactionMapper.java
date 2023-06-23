package com.example.cryptotracker.mapper;

import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.enitity.Transaction;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    Transaction toEntity(TransactionDto transactionDto);

    TransactionDto toDto(Transaction transaction);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Transaction partialUpdate(TransactionDto transactionDto, @MappingTarget Transaction transaction);
}