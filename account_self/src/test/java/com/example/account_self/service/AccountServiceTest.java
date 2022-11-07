package com.example.account_self.service;

import com.example.account_self.TestSupport;
import com.example.account_self.dto.AccountCustomerDto;
import com.example.account_self.dto.AccountDto;
import com.example.account_self.dto.CreateAccountRequest;
import com.example.account_self.dto.TransactionDto;
import com.example.account_self.dto.converter.AccountDtoConverter;
import com.example.account_self.exception.CustomerNotFoundException;
import com.example.account_self.model.Account;
import com.example.account_self.model.Customer;
import com.example.account_self.model.Transaction;
import com.example.account_self.model.TransactionType;
import com.example.account_self.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AccountServiceTest extends TestSupport {

private AccountService accountService;
private AccountRepository accountRepository;
private AccountDtoConverter accountDtoConverter;
private CustomerService customerService;
private Clock clock;

private final Customer customer=generateCustomer();
private final AccountCustomerDto accountCustomerDto=
        new AccountCustomerDto("customer-id","customer-name", "customer-surname");


@BeforeEach
    public void setup(){
    accountRepository= mock(AccountRepository.class);
    accountDtoConverter =mock(AccountDtoConverter.class);
    clock=mock(Clock.class);
    customerService=mock(CustomerService.class);
    accountService=new AccountService(accountRepository,customerService,clock,accountDtoConverter);
when(clock.instant()).thenReturn(getCurrentInstant());
    when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
}

@Test
public void testCreateAccount_whenCustomerIdExistAndInitialCreditMoreThanZero_shouldCreateAccountWithTransactions(){
    CreateAccountRequest request = generateAccountRequest(100);

    Account account = generateAccount(100);
    Transaction transaction = new Transaction(null, getLocalDateTime(), TransactionType.INITIAL, request.getInitialCredit(), account);
    account.getTransaction().add(transaction);

    TransactionDto transactionDto = new TransactionDto("", new BigDecimal(100), getLocalDateTime(), TransactionType.INITIAL);
    AccountDto expected = new AccountDto("account-id", new BigDecimal(100), getLocalDateTime(), accountCustomerDto,
            Set.of(transactionDto));

    when(customerService.findCustomerById("customer-id")).thenReturn(customer);
    when(accountRepository.save(account)).thenReturn(account);

    when(accountDtoConverter.convert(account)).thenReturn(expected);

    AccountDto result = accountService.createAccount(request);

    assertEquals(result, expected);

}


    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExist_shouldReturnCustomerWithoutTransactions() {
CreateAccountRequest accountRequest=generateAccountRequest(0);
Account account=generateAccount(0);
AccountDto expected=new AccountDto("customer-id",BigDecimal.ZERO,getLocalDateTime(),accountCustomerDto,Set.of());
when(customerService.findCustomerById("customer-id")).thenReturn(customer);
when(accountRepository.save(account)).thenReturn(account);
when(accountDtoConverter.convert(account)).thenReturn(expected);
AccountDto result=accountService.createAccount(accountRequest);
assertEquals(expected,result);


    }

    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
CreateAccountRequest accountRequest=generateAccountRequest(0);

when(customerService.findCustomerById("customer-id")).thenThrow(new CustomerNotFoundException("test-exception"));
assertThrows(CustomerNotFoundException.class,()->accountService.createAccount(accountRequest));
        verify(customerService).findCustomerById(accountRequest.getCustomerId());
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(accountDtoConverter);
}

        private Account generateAccount(int balance){
    return new Account("",new BigDecimal(balance),getLocalDateTime(),customer, new HashSet<>());
}
}