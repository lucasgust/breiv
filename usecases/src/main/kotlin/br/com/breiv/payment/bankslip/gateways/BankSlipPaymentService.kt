package br.com.breiv.payment.bankslip.gateways

import br.com.breiv.payment.PaymentProcessResponse.BankSlipPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterPayload

interface BankSlipPaymentService {

    fun process(payload: PaymentRegisterPayload): BankSlipPaymentProcessResponse
}