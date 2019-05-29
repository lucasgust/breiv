package br.com.breiv.creditcard

import java.time.LocalDate

data class CreditCard(
    val holderName: String,
    val number: String,
    val expirationDate: LocalDate,
    val cvv: String
)