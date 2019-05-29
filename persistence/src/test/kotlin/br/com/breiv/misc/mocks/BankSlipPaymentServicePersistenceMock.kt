package br.com.breiv.misc.mocks

import br.com.breiv.payment.PaymentProcessResponse.BankSlipPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterPayload
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentService
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.springframework.stereotype.Service

@Service
class BankSlipPaymentServicePersistenceMock : BankSlipPaymentService {

    override fun process(payload: PaymentRegisterPayload): BankSlipPaymentProcessResponse {
        return random(BankSlipPaymentProcessResponse::class.java)
    }
}