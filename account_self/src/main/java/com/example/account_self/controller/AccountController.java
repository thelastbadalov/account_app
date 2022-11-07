package com.example.account_self.controller;

import com.example.account_self.dto.CreateAccountRequest;
import com.example.account_self.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
@PostMapping
public ResponseEntity<?> createAccountRequest(@RequestBody CreateAccountRequest createAccountRequest){
return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }

}
