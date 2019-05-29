package br.com.breiv.payment

interface PaymentSearchInteractor {

    val paymentType: PaymentType

    fun findBy(id: Long): Payment
}