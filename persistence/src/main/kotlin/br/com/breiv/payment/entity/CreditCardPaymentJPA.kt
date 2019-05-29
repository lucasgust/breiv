package br.com.breiv.payment.entity

import br.com.breiv.buyer.entity.toJPA
import br.com.breiv.client.entity.toJPA
import br.com.breiv.creditcard.entity.CreditCardJPA
import br.com.breiv.creditcard.entity.toJPA
import br.com.breiv.misc.audit.Auditable
import br.com.breiv.payment.Payment.CreditCardPayment
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "credit_card_payment")
data class CreditCardPaymentJPA(

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "payment_id", nullable = false)
    val payment: PaymentJPA,

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "credit_card_id", nullable = false)
    val creditCard: CreditCardJPA

) : Auditable() {

    fun toEntity() = CreditCardPayment(
        status = payment.status,
        amount = payment.amount,
        client = payment.client.toEntity(),
        buyer = payment.buyer.toEntity(),
        creditCard = creditCard.toEntity()
    )
}

fun CreditCardPayment.toJPA() = CreditCardPaymentJPA(
    payment = PaymentJPA(
        amount = amount,
        status = status,
        client = client.toJPA(),
        buyer = buyer.toJPA()
    ),
    creditCard = creditCard.toJPA()
)