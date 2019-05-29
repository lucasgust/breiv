package br.com.breiv.payment.creditcard

import br.com.breiv.payment.Payment.CreditCardPayment
import br.com.breiv.payment.PaymentProcessResponse.CreditCardPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterPayload
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentRepository
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreditCardPaymentRegisterInteractorTest {

    private val paymentService: CreditCardPaymentService = mock()
    private val repository: CreditCardPaymentRepository = mock()

    private val interactor = CreditCardPaymentRegisterInteractor(
        paymentService,
        repository
    )

    @Test
    fun `should register credit card payment`() {
        val payload = random(PaymentRegisterPayload::class.java)
        val paymentResponse = random(CreditCardPaymentProcessResponse::class.java)

        val payment = CreditCardPayment(
            status = paymentResponse.status,
            amount = payload.amount,
            client = payload.client,
            buyer = payload.buyer,
            creditCard = payload.creditCard!!
        )

        val expected = paymentResponse.copy()

        whenever(paymentService.process(payload)).thenReturn(paymentResponse)
        whenever(repository.save(payment)).thenReturn(payment)

        val result = interactor.register(payload)

        assertThat(result).isEqualTo(expected)

        verify(paymentService, only()).process(payload)
        verify(repository, only()).save(payment)
    }
}