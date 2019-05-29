package br.com.breiv.payment.bankslip

import br.com.breiv.payment.Payment.BankSlipPayment
import br.com.breiv.payment.PaymentProcessResponse.BankSlipPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterInteractor
import br.com.breiv.payment.PaymentRegisterPayload
import br.com.breiv.payment.PaymentType.BANK_SLIP
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentRepository
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentService
import org.springframework.stereotype.Component

@Component
class BankSlipPaymentRegisterInteractor(
    private val paymentService: BankSlipPaymentService,
    private val repository: BankSlipPaymentRepository
) : PaymentRegisterInteractor {

    override val paymentType = BANK_SLIP

    override fun register(payload: PaymentRegisterPayload): BankSlipPaymentProcessResponse {
        val processResponse = paymentService.process(payload)

        repository.save(
            payment = BankSlipPayment(
                status = processResponse.status,
                amount = payload.amount,
                client = payload.client,
                buyer = payload.buyer,
                number = processResponse.number
            )
        )

        return processResponse
    }
}