package br.com.breiv.buyer.entity

import br.com.breiv.buyer.Buyer
import br.com.breiv.misc.audit.Auditable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "buyer")
data class BuyerJPA(

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val cpf: String

) : Auditable() {

    fun toEntity() = Buyer(
        name = name,
        email = email,
        cpf = cpf
    )
}

fun Buyer.toJPA() = BuyerJPA(
    name = name,
    email = email,
    cpf = cpf
)