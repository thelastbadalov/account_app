package com.example.account_self;
import com.example.account_self.model.Customer;
import com.example.account_self.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.Clock;
import java.util.HashSet;

@SpringBootApplication
public class AccountSelfApplication implements CommandLineRunner{

private final CustomerRepository customerRepository;

	public AccountSelfApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(AccountSelfApplication.class, args);
	}

@Bean
public Clock clock(){
		return Clock.systemUTC();
}
	@Override
	public void run(String... args) throws Exception {
		Customer customer=new Customer(null,"quli","bedelov",new HashSet<>());
		customerRepository.save(customer);
		System.out.println(customer);
	}
}
