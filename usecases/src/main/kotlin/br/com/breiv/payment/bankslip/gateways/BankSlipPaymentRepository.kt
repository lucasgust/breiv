package br.com.breiv.payment.bankslip.gateways

import br.com.breiv.payment.Payment.BankSlipPayment

interface BankSlipPaymentRepository {

    fun save(payment: BankSlipPayment): BankSlipPayment

    fun findBy(id: Long): BankSlipPayment?
}