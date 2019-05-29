package br.com.breiv.payment

import br.com.breiv.buyer.Buyer
import br.com.breiv.client.Client
import br.com.breiv.creditcard.CreditCard

sealed class Payment {

    abstract val status: PaymentStatusType
    abstract val amount: Double
    abstract val client: Client
    abstract val buyer: Buyer

    data class BankSlipPayment(
        override val status: PaymentStatusType,
        override val amount: Double,
        override val client: Client,
        override val buyer: Buyer,
        val number: String? = null
    ) : Payment()

    data class CreditCardPayment(
        override val status: PaymentStatusType,
        override val amount: Double,
        override val client: Client,
        override val buyer: Buyer,
        val creditCard: CreditCard
    ) : Payment()
}