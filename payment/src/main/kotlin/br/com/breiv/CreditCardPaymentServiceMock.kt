package br.com.breiv

import br.com.breiv.payment.PaymentProcessResponse.CreditCardPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterPayload
import br.com.breiv.payment.PaymentStatusType
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentService
import org.springframework.stereotype.Service

@Service
class CreditCardPaymentServiceMock : CreditCardPaymentService {

    /**
     * generates a random status
     */
    override fun process(payload: PaymentRegisterPayload): CreditCardPaymentProcessResponse {
        return CreditCardPaymentProcessResponse(
            status = PaymentStatusType.values()[(0 until PaymentStatusType.values().size).shuffled().first()]
        )
    }
}