package com.example.account_self;

import com.example.account_self.dto.CreateAccountRequest;
import com.example.account_self.model.Customer;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

public class TestSupport {
public static final String CUSTOMER_API_ENDPOINT="/v1/customer";
    public static final String ACCOUNT_API_ENDPOINT="/v1/account";

public Instant getCurrentInstant(){
String instantExcepted="2022-11-07T16:26:36+00:00";  //2022-11-06T20:54:17.176161
    Clock clock=Clock.fixed(Instant.parse(instantExcepted),Clock.systemDefaultZone().getZone());
    return Instant.now(clock);
}
public LocalDateTime getLocalDateTime(){
return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
}
public Customer generateCustomer(){
    return new Customer("customer-id","customer-name","customer-surname", Set.of());

}
public CreateAccountRequest generateAccountRequest(int initialCredit){

    return generateAccountRequest("customer-id",initialCredit);
}
    public CreateAccountRequest generateAccountRequest(String customerId,int initialCredit) {

        return new CreateAccountRequest(customerId, new BigDecimal(initialCredit));
    }

}
