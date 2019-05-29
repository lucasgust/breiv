package br.com.breiv

import br.com.breiv.payment.PaymentProcessResponse.BankSlipPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterPayload
import br.com.breiv.payment.PaymentStatusType.APPROVED
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentService
import org.springframework.stereotype.Service

@Service
class BankSlipPaymentServiceMock : BankSlipPaymentService {

    /**
     * generates a bank deposit slip number with approved status
     */
    override fun process(payload: PaymentRegisterPayload): BankSlipPaymentProcessResponse {
        val buffer = StringBuffer()

        repeat(24) {
            buffer.append((11..99).shuffled().first().toString())
        }

        return BankSlipPaymentProcessResponse(
            status = APPROVED,
            number = buffer.toString()
        )
    }
}