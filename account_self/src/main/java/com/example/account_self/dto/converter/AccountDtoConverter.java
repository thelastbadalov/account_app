package com.example.account_self.dto.converter;

import com.example.account_self.dto.AccountDto;
import com.example.account_self.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {
private final CustomerDtoConverter customerDtoConverter;
private final TransactionDtoConverter transactionDtoConverter;
    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto convert(Account form){
    return new AccountDto(form.getId(), form.getBalance(),form.getCreationDate(),
            customerDtoConverter.convertToAccountCustomer(Optional.ofNullable(form.getCustomer())),form.getTransaction().stream().map(
                    transactionDtoConverter::convert).collect(Collectors.toSet()));

}
}
