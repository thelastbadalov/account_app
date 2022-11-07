package com.example.account_self.dto

import com.example.account_self.model.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto (
val id:String?,
        val amount:BigDecimal?,
val transactionDate:LocalDateTime?,
val transactionType: TransactionType?=TransactionType.INITIAL,
)
