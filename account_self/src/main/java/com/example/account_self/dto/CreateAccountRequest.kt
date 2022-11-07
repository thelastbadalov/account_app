package com.example.account_self.dto

import java.math.BigDecimal

data class CreateAccountRequest(
    val customerId:String?,
    val initialCredit:BigDecimal?
)
