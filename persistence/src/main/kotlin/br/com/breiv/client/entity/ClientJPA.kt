package br.com.breiv.client.entity

import br.com.breiv.client.Client
import br.com.breiv.misc.audit.Auditable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "client")
data class ClientJPA(

    @Column(name = "client_id", nullable = false)
    val clientId: Long

) : Auditable() {

    fun toEntity() = Client(
        id = clientId
    )
}

fun Client.toJPA() = ClientJPA(
    clientId = id
)