package br.com.breiv.creditcard.entity

import br.com.breiv.creditcard.CreditCard
import br.com.breiv.misc.audit.Auditable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "credit_card")
data class CreditCardJPA(

    @Column(name = "holder_name", nullable = false)
    val holderName: String,

    @Column(nullable = false)
    val number: String,

    @Column(name = "expiration_date", nullable = false)
    val expirationDate: LocalDateTime,

    @Column(nullable = false)
    val cvv: String

) : Auditable() {

    fun toEntity() = CreditCard(
        holderName = holderName,
        number = number,
        expirationDate = expirationDate.toLocalDate(),
        cvv = cvv
    )
}

fun CreditCard.toJPA() = CreditCardJPA(
    holderName = holderName,
    number = number,
    expirationDate = expirationDate.atStartOfDay(),
    cvv = cvv
)