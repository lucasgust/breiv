package br.com.breiv.payment

sealed class PaymentProcessResponse {

    abstract val status: PaymentStatusType

    data class BankSlipPaymentProcessResponse(
        override val status: PaymentStatusType,
        val number: String
    ) : PaymentProcessResponse()

    data class CreditCardPaymentProcessResponse(
        override val status: PaymentStatusType
    ) : PaymentProcessResponse()
}