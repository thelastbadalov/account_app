package com.example.account_self.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Transaction @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id:String?="",
val transactionDate:LocalDateTime?=null,
    val transactionType: TransactionType?=TransactionType.INITIAL,
    val amount:BigDecimal?= BigDecimal.ZERO,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "account_id", nullable = false)
    val account: Account?=null,
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transaction

        if (id != other.id) return false
        if (transactionType != other.transactionType) return false
        if (amount != other.amount) return false
        if (transactionDate != other.transactionDate) return false
        if (account != other.account) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (transactionType?.hashCode() ?: 0)
        result = 31 * result + (amount?.hashCode() ?: 0)
        result = 31 * result + (transactionDate?.hashCode() ?: 0)
        result = 31 * result + account.hashCode()
        return result
    }

    override fun toString(): String {
        return "Transaction(id=$id, " +
                "transactionType=$transactionType, " +
                "amount=$amount," + "" + " " +
                "account=$account)"
    }


}


enum class TransactionType {
    INITIAL, TRANSFER
}