package br.com.breiv.payment.entity

import br.com.breiv.buyer.entity.BuyerJPA
import br.com.breiv.client.entity.ClientJPA
import br.com.breiv.misc.audit.Auditable
import br.com.breiv.payment.PaymentStatusType
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "payment")
data class PaymentJPA(

    @Column
    @Enumerated(EnumType.STRING)
    val status: PaymentStatusType,

    @Column(nullable = false)
    val amount: Double,

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "client_id", nullable = false)
    val client: ClientJPA,

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "buyer_id", nullable = false)
    val buyer: BuyerJPA

) : Auditable()