package com.example.account_self.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Account @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id:String?="",
    val balance:BigDecimal?= BigDecimal.ZERO,
    val creationDate:LocalDateTime?=null,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
@JoinColumn(name = "customer_id", nullable = false)
val customer: Customer?=null,

@OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
val transaction: Set<Transaction> =HashSet()
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (id != other.id) return false
        if (balance != other.balance) return false
        if (creationDate != other.creationDate) return false
        if (customer != other.customer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (balance?.hashCode() ?: 0)
        result = 31 * result + creationDate.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Account(id=$id," +
                " balance=$balance,  customer=$customer, transaction=$transaction)"
    }
}
