package br.com.breiv.payment.creditcard

import br.com.breiv.payment.Payment.CreditCardPayment
import br.com.breiv.payment.PaymentProcessResponse.CreditCardPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterInteractor
import br.com.breiv.payment.PaymentRegisterPayload
import br.com.breiv.payment.PaymentType.CREDIT_CARD
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentRepository
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentService
import org.springframework.stereotype.Component

@Component
class CreditCardPaymentRegisterInteractor(
    private val paymentService: CreditCardPaymentService,
    private val repository: CreditCardPaymentRepository
) : PaymentRegisterInteractor {

    override val paymentType = CREDIT_CARD

    override fun register(payload: PaymentRegisterPayload): CreditCardPaymentProcessResponse {
        val processResponse = paymentService.process(payload)

        repository.save(
            payment = CreditCardPayment(
                status = processResponse.status,
                amount = payload.amount,
                client = payload.client,
                buyer = payload.buyer,
                creditCard = payload.creditCard!!
            )
        )

        return processResponse
    }
}