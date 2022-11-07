package com.example.account_self.service;

import com.example.account_self.dto.CustomerDto;
import com.example.account_self.dto.converter.CustomerDtoConverter;
import com.example.account_self.exception.CustomerNotFoundException;
import com.example.account_self.model.Customer;
import com.example.account_self.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
private final CustomerRepository customerRepository;
private final CustomerDtoConverter customerDtoConverter;


    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;

    }
protected Customer findCustomerById(String id){
return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException
        ("can not find customer with given id"+id));


    }

public CustomerDto getCustomerById(String customerId){
        return customerDtoConverter.convertToCustomer(findCustomerById(customerId));

}

public List<CustomerDto> getAllCustomers(){
        return customerRepository.findAll().stream().map(customerDtoConverter::convertToCustomer).
                collect(Collectors.toList());
}
}
