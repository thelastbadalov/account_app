package com.example.account_self.service;

import com.example.account_self.dto.AccountDto;
import com.example.account_self.dto.CreateAccountRequest;
import com.example.account_self.dto.converter.AccountDtoConverter;
import com.example.account_self.model.Account;
import com.example.account_self.model.Customer;
import com.example.account_self.model.Transaction;
import com.example.account_self.model.TransactionType;
import com.example.account_self.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AccountService {

private final AccountRepository accountRepository;
private final CustomerService customerService;
private final Clock clock;
private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService, Clock clock, AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.clock = clock;
        this.accountDtoConverter = accountDtoConverter;
    }


public AccountDto createAccount(CreateAccountRequest createAccountRequest){
    Customer customer=customerService.findCustomerById(createAccountRequest.getCustomerId());
    Account account=new Account("",createAccountRequest.getInitialCredit(),getLocalDateTimeNow(),customer);
if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO)>0){
    Transaction transaction=new Transaction(null,getLocalDateTimeNow(), TransactionType.INITIAL,
            createAccountRequest.getInitialCredit(),account);
account.getTransaction().add(transaction);
}
    return accountDtoConverter.convert(accountRepository.save(account));
    }



private LocalDateTime getLocalDateTimeNow(){
Instant instant=clock.instant();
    return LocalDateTime.ofInstant(instant,Clock.systemDefaultZone().getZone());
}

}
