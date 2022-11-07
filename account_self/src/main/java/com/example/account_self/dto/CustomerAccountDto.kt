package com.example.account_self.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class CustomerAccountDto(
    val id:String,
    val creationDate: LocalDateTime?,
    val balance:BigDecimal?= BigDecimal.ZERO,
    val transaction:Set<TransactionDto>,
)
