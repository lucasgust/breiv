package br.com.breiv.payment.creditcard

import br.com.breiv.payment.Payment
import br.com.breiv.payment.PaymentSearchInteractor
import br.com.breiv.payment.PaymentType.CREDIT_CARD
import br.com.breiv.payment.creditcard.exceptions.CreditCardPaymentNotFoundException
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentRepository
import org.springframework.stereotype.Component

@Component
class CreditCardPaymentSearchInteractor(
    private val repository: CreditCardPaymentRepository
) : PaymentSearchInteractor {

    override val paymentType = CREDIT_CARD

    override fun findBy(id: Long): Payment {
        return repository.findBy(id) ?: throw CreditCardPaymentNotFoundException("Credit Card payment $id not found")
    }
}