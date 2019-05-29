package br.com.breiv.payment

import org.springframework.stereotype.Component

@Component
class PaymentInteractorFactory(
    private val paymentRegisterInteractors: List<PaymentRegisterInteractor>,
    private val paymentSearchInteractors: List<PaymentSearchInteractor>
) {

    fun getPaymentRegisterInteractorBy(paymentType: PaymentType) =
        paymentRegisterInteractors.find { it.paymentType == paymentType }
            ?: throw RuntimeException("No payment register interactor found for $paymentType")

    fun getPaymentSearchInteractorBy(paymentType: PaymentType) =
        paymentSearchInteractors.find { it.paymentType == paymentType }
            ?: throw RuntimeException("No payment search interactor found for $paymentType")
}