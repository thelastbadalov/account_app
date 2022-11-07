package com.example.account_self.service;

import com.example.account_self.TestSupport;
import com.example.account_self.dto.CustomerDto;
import com.example.account_self.dto.converter.CustomerDtoConverter;
import com.example.account_self.exception.CustomerNotFoundException;
import com.example.account_self.model.Customer;
import com.example.account_self.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceTest extends TestSupport {

    private CustomerRepository customerRepository;
private CustomerDtoConverter converter;
private CustomerService customerService;

@BeforeEach
    public void setup(){
    customerRepository= mock(CustomerRepository.class);
converter=mock(CustomerDtoConverter.class);
customerService=new CustomerService(customerRepository,converter);
    }

@Test
    public void testCustomerId_whenCustomerIdExist_shouldReturnCustomer(){
    Customer customer=generateCustomer();
    when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
    Customer result=customerService.findCustomerById("customer-id");
    Assert.assertEquals(customer,result);
}
    @Test
    public void testCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        when(customerRepository.findById("customer-id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,()->customerService.findCustomerById("customer-id"));
    }
@Test
    public void testGetCustomerById_whenCustomerIdExist_shouldReturnCustomer(){
    Customer customer=generateCustomer();
    CustomerDto customerDto=new CustomerDto("customer-id","name","surname", Set.of());
when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
when(converter.convertToCustomer(customer)).thenReturn(customerDto);
CustomerDto result=customerService.getCustomerById("customer-id");
assertEquals(customerDto,result);

}
    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
    when(customerRepository.findById("customer-id")).thenReturn(Optional.empty());
    assertThrows(CustomerNotFoundException.class,()-> customerService.getCustomerById("customer-id"));
        Mockito.verifyNoInteractions(converter);
    }


}