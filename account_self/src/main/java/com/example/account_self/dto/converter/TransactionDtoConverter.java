package com.example.account_self.dto.converter;

import com.example.account_self.dto.TransactionDto;
import com.example.account_self.model.Transaction;
import org.springframework.stereotype.Component;


@Component
public class TransactionDtoConverter {

public TransactionDto convert (Transaction form){
    return new TransactionDto(form.getId(),form.getAmount(),form.getTransactionDate(),form.getTransactionType());
}
}
