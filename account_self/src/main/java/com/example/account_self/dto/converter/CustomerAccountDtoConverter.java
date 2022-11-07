package com.example.account_self.dto.converter;

import com.example.account_self.dto.CustomerAccountDto;
import com.example.account_self.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {
private final TransactionDtoConverter transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public CustomerAccountDto convert(Account form){
        return new CustomerAccountDto(form.getId(), form.getCreationDate(), form.getBalance(),form.getTransaction().stream()
                        .map(transactionDtoConverter::convert).collect(Collectors.toSet()));
    }

}
