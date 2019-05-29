package br.com.breiv.payment.entity

import br.com.breiv.buyer.entity.toJPA
import br.com.breiv.client.entity.toJPA
import br.com.breiv.misc.audit.Auditable
import br.com.breiv.payment.Payment.BankSlipPayment
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "bank_slip_payment")
data class BankSlipPaymentJPA(

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "payment_id", nullable = false)
    val payment: PaymentJPA,

    @Column
    val number: String?

) : Auditable() {

    fun toEntity() = BankSlipPayment(
        status = payment.status,
        amount = payment.amount,
        client = payment.client.toEntity(),
        buyer = payment.buyer.toEntity(),
        number = number
    )
}

fun BankSlipPayment.toJPA() = BankSlipPaymentJPA(
    payment = PaymentJPA(
        status = status,
        amount = amount,
        client = client.toJPA(),
        buyer = buyer.toJPA()
    ),
    number = number
)