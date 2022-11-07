package com.example.account_self.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Customer @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
val id:String?="",
    val name:String?=null,
    val surname:String?=null,
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    val account:Set<Account>?=null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        if (id != other.id) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (account != other.account) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (surname?.hashCode() ?: 0)
        return result
    }
}

