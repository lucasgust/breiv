package br.com.breiv.misc.mocks

import br.com.breiv.payment.PaymentProcessResponse.CreditCardPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterPayload
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentService
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.springframework.stereotype.Service

@Service
class CreditCardPaymentServicePersistenceMock : CreditCardPaymentService {

    override fun process(payload: PaymentRegisterPayload): CreditCardPaymentProcessResponse {
        return random(CreditCardPaymentProcessResponse::class.java)
    }
}