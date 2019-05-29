package br.com.breiv.payment.gateways

import br.com.breiv.misc.utils.getIfPresent
import br.com.breiv.payment.Payment.CreditCardPayment
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentRepository
import br.com.breiv.payment.entity.toJPA
import br.com.breiv.payment.repository.CreditCardPaymentDAO
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CreditCardPaymentRepositoryImpl(
    private val creditCardPaymentDAO: CreditCardPaymentDAO
) : CreditCardPaymentRepository {

    @Transactional
    override fun save(payment: CreditCardPayment): CreditCardPayment {
        return creditCardPaymentDAO.save(payment.toJPA()).toEntity()
    }

    override fun findBy(id: Long): CreditCardPayment? {
        return creditCardPaymentDAO.findById(id).getIfPresent()?.toEntity()
    }
}