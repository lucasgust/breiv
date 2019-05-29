package br.com.breiv.payment.creditcard.gateways

import br.com.breiv.payment.PaymentProcessResponse.CreditCardPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterPayload

interface CreditCardPaymentService {

    fun process(payload: PaymentRegisterPayload): CreditCardPaymentProcessResponse
}