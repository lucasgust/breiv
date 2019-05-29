package br.com.breiv.payment.creditcard.gateways

import br.com.breiv.payment.Payment.CreditCardPayment

interface CreditCardPaymentRepository {

    fun save(payment: CreditCardPayment): CreditCardPayment

    fun findBy(id: Long): CreditCardPayment?
}