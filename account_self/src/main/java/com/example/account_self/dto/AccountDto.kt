package com.example.account_self.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountDto(
    val id:String?,
    val balance:BigDecimal?,
    val creationDate:LocalDateTime?,
    val customer: AccountCustomerDto?,
    val transaction:Set<TransactionDto>?


)
