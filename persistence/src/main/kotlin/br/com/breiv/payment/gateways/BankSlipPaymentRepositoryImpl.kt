package br.com.breiv.payment.gateways

import br.com.breiv.misc.utils.getIfPresent
import br.com.breiv.payment.Payment.BankSlipPayment
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentRepository
import br.com.breiv.payment.entity.toJPA
import br.com.breiv.payment.repository.BankSlipPaymentDAO
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class BankSlipPaymentRepositoryImpl(
    private val bankSlipPaymentDAO: BankSlipPaymentDAO
) : BankSlipPaymentRepository {

    @Transactional
    override fun save(payment: BankSlipPayment): BankSlipPayment {
        return bankSlipPaymentDAO.save(payment.toJPA()).toEntity()
    }

    override fun findBy(id: Long): BankSlipPayment? {
        return bankSlipPaymentDAO.findById(id).getIfPresent()?.toEntity()
    }
}