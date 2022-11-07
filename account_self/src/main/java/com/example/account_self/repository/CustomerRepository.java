package com.example.account_self.repository;

import com.example.account_self.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer,String> {
}
