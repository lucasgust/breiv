package br.com.breiv.payment

import br.com.breiv.buyer.Buyer
import br.com.breiv.client.Client
import br.com.breiv.creditcard.CreditCard

interface PaymentRegisterInteractor {

    val paymentType: PaymentType

    fun register(payload: PaymentRegisterPayload): PaymentProcessResponse
}

data class PaymentRegisterPayload(
    val paymentType: PaymentType,
    val client: Client,
    val buyer: Buyer,
    val amount: Double,
    val creditCard: CreditCard? = null
)