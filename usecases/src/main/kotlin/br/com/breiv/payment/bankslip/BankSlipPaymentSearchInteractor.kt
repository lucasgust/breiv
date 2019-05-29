package br.com.breiv.payment.bankslip

import br.com.breiv.payment.Payment.BankSlipPayment
import br.com.breiv.payment.PaymentSearchInteractor
import br.com.breiv.payment.PaymentType.BANK_SLIP
import br.com.breiv.payment.bankslip.exceptions.BankSlipPaymentNotFoundException
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentRepository
import org.springframework.stereotype.Component

@Component
class BankSlipPaymentSearchInteractor(
    private val repository: BankSlipPaymentRepository
) : PaymentSearchInteractor {

    override val paymentType = BANK_SLIP

    override fun findBy(id: Long): BankSlipPayment {
        return repository.findBy(id) ?: throw BankSlipPaymentNotFoundException("Bank slip payment $id not found")
    }
}