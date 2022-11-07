package com.example.account_self.dto.converter;

import com.example.account_self.dto.AccountCustomerDto;
import com.example.account_self.dto.CustomerDto;
import com.example.account_self.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
private final CustomerAccountDtoConverter converter;

    public CustomerDtoConverter(CustomerAccountDtoConverter converter) {
        this.converter = converter;
    }

    public AccountCustomerDto convertToAccountCustomer(Optional<Customer> form){
        return form.map(f-> new AccountCustomerDto(f.getId(), f.getName(), f.getSurname())).orElse(null);
    }


public CustomerDto convertToCustomer(Customer form){
    return  new CustomerDto(form.getId(),form.getName(),form.getSurname(),form.getAccount().stream()
            .map(converter::convert).collect(Collectors.toSet()));
}
}
